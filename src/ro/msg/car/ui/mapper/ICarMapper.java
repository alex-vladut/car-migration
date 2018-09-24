package ro.msg.car.ui.mapper;

import ro.msg.car.entity.Car;

public interface ICarMapper {

	Car mapInput(String[] values);
	
	String[] mapEntity(Car car);
}
