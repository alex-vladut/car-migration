package ro.msg.car.service.price.impl;

import java.util.HashMap;
import java.util.Map;

import ro.msg.car.entity.CarType;
import ro.msg.car.logger.ILogger;
import ro.msg.car.service.price.IPriceCalculatorFactory;
import ro.msg.car.service.price.IPriceCalculatorStrategy;

public class PriceCalculatorFactory implements IPriceCalculatorFactory {
	private final Map<CarType, IPriceCalculatorStrategy> calculatorStrategies;
	private final ILogger logger;
	
	public PriceCalculatorFactory(final ILogger logger) {
		this.logger = logger;
		this.calculatorStrategies = new HashMap<CarType, IPriceCalculatorStrategy>();
	}
	
	public PriceCalculatorFactory(
			final ILogger logger,
			final Map<CarType, IPriceCalculatorStrategy> calculatorStrategies) {
		this.logger = logger;
		this.calculatorStrategies = calculatorStrategies;
	}
	
	@Override
	public void setDefaultPriceCalculator(final IPriceCalculatorStrategy priceCalculator) {
		addPriceCalculator(CarType.UNKNOWN, priceCalculator);
	}
	
	@Override
	public void addPriceCalculator(final CarType carType, final IPriceCalculatorStrategy priceCalculator) {
		if(carType == null) {
			throw new IllegalArgumentException("Car type should not be null!");
		}
		if(priceCalculator == null) {
			throw new IllegalArgumentException("Price strategy should not be null!");
		}
		if(calculatorStrategies.containsKey(carType)) {
			logger.logWarning("The price strategy for '%s' was overriden!", carType);
		}
		
		calculatorStrategies.put(carType, priceCalculator);
	}
	
	@Override
	public void removePriceCalculator(final CarType carType) {
		if(calculatorStrategies.containsKey(carType)) {
			calculatorStrategies.remove(carType);
		}else {
			logger.logWarning("No registered strategy found for car type '%s'", carType);
		}
	}
	
	@Override
	public IPriceCalculatorStrategy getPriceStrategy(final CarType carType) {
		if(calculatorStrategies.containsKey(carType)) {
			return calculatorStrategies.get(carType);
		}else {
			return calculatorStrategies.get(CarType.UNKNOWN);
		}
	}
}
