package ro.msg.car.persistence.impl;

import java.sql.SQLException;
import java.util.List;

import ro.msg.car.entity.Car;
import ro.msg.car.logger.ILogger;
import ro.msg.car.persistence.ICarMigrationPersistence;

public class CarPersistenceLoggingDecorator implements ICarMigrationPersistence {
	
	private final ICarMigrationPersistence persistence;
	private final ILogger logger;
	
	public CarPersistenceLoggingDecorator(
			final ICarMigrationPersistence persistence,
			final ILogger logger) {
		this.persistence = persistence;
		this.logger = logger;
	}
	
	@Override
	public void saveCar(Car car) throws SQLException {
		logger.logDebug("> CarPersistence.saveCar(Car)");
		final long start = System.currentTimeMillis();
		
		persistence.saveCar(car);
		
		final long end = System.currentTimeMillis();
		logger.logDebug("< CarPersistence.saveCar(Car) executed in %d ms.", (end-start));
	}

	@Override
	public List<Car> findAllCars() throws SQLException {
		logger.logDebug("> CarPersistence.findAllCars()");
		final long start = System.currentTimeMillis();
		
		final List<Car> result = persistence.findAllCars();
		
		final long end = System.currentTimeMillis();
		logger.logDebug("< CarPersistence.findAllCars() executed in %d ms.", (end-start));
		
		return result;
	}

}
