package ro.msg.car.ui.controller.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ro.msg.car.entity.Car;
import ro.msg.car.logger.ILogger;
import ro.msg.car.service.ICarMigrationService;
import ro.msg.car.ui.controller.ICarMigrationController;
import ro.msg.car.ui.mapper.ICarMapper;
import ro.msg.car.ui.validator.IInputValidator;

public class SysoCarMigrationController implements ICarMigrationController {
	private final ILogger logger;
	private final ICarMigrationService service;
	private final IInputValidator validator;
	private final ICarMapper mapper;
	
	public SysoCarMigrationController(
			final ICarMigrationService service,
			final IInputValidator validator,
			final ICarMapper mapper,
			final ILogger logger) {
		this.service = service;
		this.validator = validator;
		this.mapper = mapper;
		this.logger = logger;
	}

	@Override
	public void saveInputEntries(final List<String[]> lines) {
		final List<Car> carEntities = convertInputLinesToEntities(lines);
		
		this.service.saveCars(carEntities);
	}
	
	@Override
	public List<String[]> findAllMigratedEntries(){
		final List<Car> cars = this.service.finadAllCars();
		
		final List<String[]> result = convertEntitiesToOutputLines(cars);
		
		return result;
	}

	private List<String[]> convertEntitiesToOutputLines(final List<Car> cars) {
		final List<String[]> result = new ArrayList<String[]>();
		
		for(final Car car : cars) {
			result.add(this.mapper.mapEntity(car));
		}
		
		return result;
	}
	
	private List<Car> convertInputLinesToEntities(final List<String[]> lines) {
		final List<Car> result = new ArrayList<Car>();
		for (final String[] splits : lines) {
			if (validator.isValid(splits)) {
				final Car car = mapper.mapInput(splits);
				result.add(car);
			} else {
				logger.logInfo("The following line is invalid and was skipped: %s ", Arrays.toString(splits));
			}
		}

		return result;
	}
}
