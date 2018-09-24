package ro.msg.car.service;

import java.util.List;

import ro.msg.car.entity.Car;

public interface ICarMigrationService {

	void saveCars(final List<Car> cars);
	
	List<Car> finadAllCars();
}
