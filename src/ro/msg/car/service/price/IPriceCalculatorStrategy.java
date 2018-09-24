package ro.msg.car.service.price;

import ro.msg.car.entity.Car;

public interface IPriceCalculatorStrategy {

	double calculateCarPrice(Car car);
}
