package ro.msg.car.persistence.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import ro.msg.car.entity.Car;
import ro.msg.car.entity.CarType;
import ro.msg.car.persistence.mapper.IResultSetMapper;

public class JdbcResultSetMapper implements IResultSetMapper {

	@Override
	public Car mapRow(final ResultSet rs) throws SQLException {
		final Car result = new Car();
		
		result.setId(rs.getInt(1));
		result.setMake(rs.getString(2));
		result.setModel(rs.getString(3));
		result.setYear(rs.getInt(4));
		result.setCarType(CarType.getCarType(rs.getString(5)));
		result.setPrice(rs.getDouble(6));
		result.setSpecifications(rs.getString(7));
		
		return result;
	}

}
