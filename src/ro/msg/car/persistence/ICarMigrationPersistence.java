package ro.msg.car.persistence;

import java.sql.SQLException;
import java.util.List;

import ro.msg.car.entity.Car;

public interface ICarMigrationPersistence {

	void saveCar(final Car car) throws SQLException;
	
	List<Car> findAllCars() throws SQLException;
}
