package ro.msg.car.service.impl;

import java.util.List;

import ro.msg.car.entity.Car;
import ro.msg.car.logger.ILogger;
import ro.msg.car.service.ICarMigrationService;

public class CarServiceLoggingDecorator implements ICarMigrationService {

	private final ILogger logger;
	private final ICarMigrationService service;
	
	public CarServiceLoggingDecorator(
			final ICarMigrationService service,
			final ILogger logger) {
		this.service = service;
		this.logger = logger;
	}
	
	@Override
	public void saveCars(final List<Car> cars) {
		logger.logDebug("> CarService.saveCars(List<Car>)");
		final long start = System.currentTimeMillis();
		
		service.saveCars(cars);
		
		final long end = System.currentTimeMillis();
		logger.logDebug("< CarService.saveCars(List<Car>) executed in %d ms.", (end-start));
	}

	@Override
	public List<Car> finadAllCars() {
		logger.logDebug("> CarService.finadAllCars()");
		final long start = System.currentTimeMillis();
		
		final List<Car> result = service.finadAllCars();
		
		final long end = System.currentTimeMillis();
		logger.logDebug("< CarService.finadAllCars() executed in %d ms.", (end-start));
		
		return result;
	}

}
