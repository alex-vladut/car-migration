package ro.msg.car.service.price.impl;

import ro.msg.car.extern.service.IPriceService;

public class SUVPriceCalculatorStrategy extends AbstractPriceCalculatorStrategy {

	private static final double PERCENTAGE_VALUE_DECREASE_PER_YEAR = 0.097;

	public SUVPriceCalculatorStrategy(final IPriceService priceService) {
		super(priceService);
	}

	@Override
	protected double calculateCarPrice(
			final int ageInYears, 
			final double standardPrice,
			final String specifications) {
		final double actualPrice = standardPrice - standardPrice * ageInYears * PERCENTAGE_VALUE_DECREASE_PER_YEAR;
		
		return actualPrice;
	}

}
