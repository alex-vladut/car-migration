package ro.msg.car.service.price;

import ro.msg.car.entity.CarType;


public interface IPriceCalculatorFactory {
	
	void setDefaultPriceCalculator(IPriceCalculatorStrategy priceCalculator);
	
	void addPriceCalculator(CarType carType, IPriceCalculatorStrategy priceCalculator);
	
	void removePriceCalculator(CarType carType);

	IPriceCalculatorStrategy getPriceStrategy(CarType carType);
}
