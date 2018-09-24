package ro.msg.car.extern.service.impl;

import ro.msg.car.extern.service.IPriceService;

public class PriceService implements IPriceService {

	@Override
	public double standardPrice(String make, String model) {
		return 10_000;
	}
}
