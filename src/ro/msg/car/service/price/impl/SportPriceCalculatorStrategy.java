package ro.msg.car.service.price.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ro.msg.car.extern.service.IPriceService;

public class SportPriceCalculatorStrategy extends AbstractPriceCalculatorStrategy {
	private static final double PERCENTAGE_VALUE_DECREASE_PER_YEAR = 0.09;
	private static final Pattern patternTunning = Pattern.compile("tunning=([0-9]*)");
	
	public SportPriceCalculatorStrategy(final IPriceService priceService) {
		super(priceService);
	}
	
	@Override
	protected double calculateCarPrice(final int ageInYears, final double standardPrice, final String specifications) {
		final double actualPrice = standardPrice - standardPrice * ageInYears * PERCENTAGE_VALUE_DECREASE_PER_YEAR + getTunningValue(specifications);
		
		return actualPrice;
	}

	private double getTunningValue(final String specifications) {
		if(specifications == null || specifications.isEmpty()) {
			return 0;
		}
		final Matcher matcher = patternTunning.matcher(specifications);
		if(matcher.find()) {
			final int value = Integer.valueOf(matcher.group(1));
			return value * 0.70;
		}
		return 0;
	}

}
