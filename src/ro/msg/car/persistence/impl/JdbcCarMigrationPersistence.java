package ro.msg.car.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ro.msg.car.entity.Car;
import ro.msg.car.logger.ILogger;
import ro.msg.car.persistence.ICarMigrationPersistence;
import ro.msg.car.persistence.mapper.IResultSetMapper;

public class JdbcCarMigrationPersistence implements ICarMigrationPersistence {
	private final ILogger logger;
	private IResultSetMapper mapper;
	
	public JdbcCarMigrationPersistence(final ILogger logger) {
		if(logger == null) {
			throw new NullPointerException("The logger should not be null!");
		}
		
		this.logger = logger;
	}
	
	@Override
	public void saveCar(final Car car) throws SQLException {
		final Statement statement = getConnection().createStatement();
		final String sqlStatement = createSaveSqlStatement(car);
		logger.logInfo("%s", sqlStatement);
		statement.execute(sqlStatement);
		statement.close();
	}

	private String createSaveSqlStatement(final Car car) {
		return "INSERT INTO CARS (make, model, year_prod, type, price, specifications) VALUES ("
						+ "'" + car.getMake() + "', '"
						+ car.getModel() + "',"
						+ car.getYear()	+ ", '"
						+ car.getCarType().getValue() + "', "
						+ car.getPrice() + ", '"
						+ car.getSpecifications() + "')";
	}
	
	@Override
	public List<Car> findAllCars() throws SQLException {
		final List<Car> result = new ArrayList<Car>();
		final Statement statement = getConnection().createStatement();
		statement.execute("SELECT * FROM CARS");
		final ResultSet rs = statement.getResultSet();
		while (rs.next()) {
			final Car car = this.mapper.mapRow(rs);
			result.add(car);
		}
		rs.close();
		statement.close();
		return result;
	}
	
	private Connection getConnection() {
		return DbConnectionFactory.getConnection();
	}
	
	public void setRowMapper(final IResultSetMapper mapper) {
		if(mapper == null) {
			throw new NullPointerException("The argument should not be null!");
		}
		
		this.mapper = mapper;
	}
	
}
