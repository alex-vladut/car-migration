package ro.msg.car.persistence.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbConnectionFactory {
	private static final String KEY_SQL_SCRIPTS = "db.sql.scripts";
	private static final String KEY_URL = "db.URL";
	private static final String KEY_DRIVER_CLASS = "db.driverClass";
	private static final String PATH_DATABASE_PROPERTIES = "database/database.properties";
	
	private static Connection instance;
	private static final Properties properties = new Properties();
	
	static {
		final InputStream inputStream = DbConnectionFactory.class.getClassLoader().getResourceAsStream(PATH_DATABASE_PROPERTIES);
		try {
			if(inputStream != null) {
				properties.load(inputStream);
			}else {
				throw new FileNotFoundException(PATH_DATABASE_PROPERTIES);
			}
		} catch (IOException e) {
			logMessage("Cannot load database properties file: " + e.getLocalizedMessage());
		}finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				logMessage("Error during close InputStream: " + e.getLocalizedMessage());
			}
		}
	}
	
	
	public static synchronized Connection getConnection() {
		if(instance == null) {
			try {
				instance = createNewDatabaseConnection();
			} catch (SQLException e) {
				logMessage("ERROR: An error has occoured by creating a new Database-Connection: " + e.getLocalizedMessage());
			}
		}
		
		return instance;
	}
	
	private static Connection createNewDatabaseConnection() throws SQLException {
		try {
			final String jdbcDriverClass = properties.getProperty(KEY_DRIVER_CLASS);
			Class.forName(jdbcDriverClass).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		final String databaseURL = properties.getProperty(KEY_URL);
		final Connection result = DriverManager.getConnection(databaseURL);

		runInitializationScripts(result);

		return result;
	}

	private static void runInitializationScripts(final Connection conn) throws SQLException {
		final String[] scriptFiles = properties.getProperty(KEY_SQL_SCRIPTS).split(";");
		for(final String fileName : scriptFiles) {
			final String script = readContentFromFile(fileName);
			final Statement statement = conn.createStatement();
			statement.execute(script);
			statement.close();
		}
		
	}
	
	private static String readContentFromFile(final String fileName) {
		final StringBuilder result = new StringBuilder();
		final URL url = DbConnectionFactory.class.getClassLoader().getResource(fileName);
		try(final BufferedReader reader = new BufferedReader(new FileReader(url.getPath()))){
			String line;
			while((line = reader.readLine()) != null) {
				result.append(line).append("\n");
			}
		} catch (IOException e) {
			logMessage("An error occoured during reading the file: " + fileName);
		}
		
		return result.toString();
	}

	private static void logMessage(final String message) {
		System.out.println(message);
	}
}
