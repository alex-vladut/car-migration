package ro.msg.car.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ro.msg.car.entity.Car;

public interface IResultSetMapper {

	Car mapRow(ResultSet rs)  throws SQLException;
}
