package ro.msg.car.ui.mapper.impl;

import ro.msg.car.entity.Car;
import ro.msg.car.entity.CarType;
import ro.msg.car.ui.mapper.ICarMapper;

public class SimpleCarMapper implements ICarMapper {

	@Override
	public Car mapInput(final String[] values) {
		final Car car = new Car();
		
		car.setMake(trim(values[0]));
		car.setModel(trim(values[1]));
		car.setYear(Integer.valueOf(trim(values[2])));
		car.setCarType(CarType.getCarType(trim(values[3])));
		car.setSpecifications(trim(values[4]));
		
		return car;
	}
	
	@Override
	public String[] mapEntity(final Car car) {
		return new String[] {
				car.getMake(), car.getModel(), 
				car.getCarType().getValue(), 
				String.valueOf(car.getYear()),
				String.valueOf(car.getPrice()), 
				car.getSpecifications()};
	}
	
	private String trim(final String value) {
		if(value == null) {
			return null;
		} else {
			return value.trim();
		}
	}
}
