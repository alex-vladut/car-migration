package ro.msg.car.service.price.impl;

import java.util.Calendar;

import ro.msg.car.entity.Car;
import ro.msg.car.extern.service.IPriceService;
import ro.msg.car.service.price.IPriceCalculatorStrategy;

public abstract class AbstractPriceCalculatorStrategy implements IPriceCalculatorStrategy {
	private final IPriceService priceService;
	
	public AbstractPriceCalculatorStrategy(final IPriceService priceService) {
		this.priceService = priceService;
	}
	
	@Override
	public final double calculateCarPrice(final Car car) {
		final double standardPrice = getStandardPrice(car);
		final int yearsOld = calculateAge(car);
		
		return calculateCarPrice(yearsOld, standardPrice, car.getSpecifications());
	}

	private int calculateAge(final Car car) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		final int age = currentYear - car.getYear();
		return age;
	}

	private double getStandardPrice(Car car) {
		return priceService.standardPrice(car.getMake(), car.getModel());
	}

	protected abstract double calculateCarPrice(int ageInYears, double standardPrice, String specifications);

}
