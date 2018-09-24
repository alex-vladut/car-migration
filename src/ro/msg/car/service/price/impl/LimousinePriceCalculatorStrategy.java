package ro.msg.car.service.price.impl;

import ro.msg.car.extern.service.IPriceService;

public class LimousinePriceCalculatorStrategy extends AbstractPriceCalculatorStrategy {
	private static final double PERCENTAGE_VALUE_DECREASE_PER_YEAR = 0.03;

	public LimousinePriceCalculatorStrategy(final IPriceService priceService) {
		super(priceService);
	}
	
	@Override
	protected double calculateCarPrice(final int ageInYears, final double standardPrice, final String specifications) {
		final double actualPrice = standardPrice - standardPrice * ageInYears * PERCENTAGE_VALUE_DECREASE_PER_YEAR;
		
		return actualPrice;
	}

}
