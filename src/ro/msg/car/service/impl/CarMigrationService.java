package ro.msg.car.service.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import ro.msg.car.entity.Car;
import ro.msg.car.logger.ILogger;
import ro.msg.car.persistence.ICarMigrationPersistence;
import ro.msg.car.service.ICarMigrationService;
import ro.msg.car.service.price.IPriceCalculatorFactory;
import ro.msg.car.service.price.IPriceCalculatorStrategy;

public class CarMigrationService implements ICarMigrationService {
	private final ILogger logger;
	private final ICarMigrationPersistence persistence;
	private final IPriceCalculatorFactory calculatorFactory;
	
	public CarMigrationService(
			final ICarMigrationPersistence persistence,
			final IPriceCalculatorFactory calculatorFactory,
			final ILogger logger) {
		this.persistence = persistence;
		this.calculatorFactory = calculatorFactory;
		this.logger = logger;
	}
	
	@Override
	public void saveCars(final List<Car> cars) {
		for (final Car car : cars) {
			final IPriceCalculatorStrategy calculatorStrategy = calculatorFactory.getPriceStrategy(car.getCarType());
			final double actualPrice = calculatorStrategy.calculateCarPrice(car);
			
			car.setPrice(actualPrice);
			
			try {
				persistence.saveCar(car);
			} catch (SQLException e) {
				logger.logError("The entry cannot be saved due to the following exception: %s", e.getLocalizedMessage());
			}
		}
	}
	
	@Override
	public List<Car> finadAllCars() {
		try {
			return this.persistence.findAllCars();
		} catch (SQLException e) {
			logger.logError("Reading saved entries is not possible: %s", e.getLocalizedMessage());
			
			return Collections.emptyList();
		}
	}
	
}
