package ro.msg.car.service.price.impl;

import ro.msg.car.entity.Car;
import ro.msg.car.logger.ILogger;
import ro.msg.car.service.price.IPriceCalculatorStrategy;

public class DefaultPriceCalculatorStrategy implements IPriceCalculatorStrategy {
	private final ILogger logger;
	
	public DefaultPriceCalculatorStrategy(final ILogger logger) {
		this.logger = logger;
	}
	
	@Override
	public double calculateCarPrice(final Car car) {
		logger.logWarning("No valid car type found for '%s'!", car.getCarType());
		
		return 0;
	}

}
