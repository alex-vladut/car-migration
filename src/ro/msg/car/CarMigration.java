package ro.msg.car;

import ro.msg.car.entity.CarType;
import ro.msg.car.extern.service.IPriceService;
import ro.msg.car.extern.service.impl.PriceService;
import ro.msg.car.logger.ILogger;
import ro.msg.car.logger.impl.SysoLogger;
import ro.msg.car.persistence.ICarMigrationPersistence;
import ro.msg.car.persistence.impl.CarPersistenceLoggingDecorator;
import ro.msg.car.persistence.impl.JdbcCarMigrationPersistence;
import ro.msg.car.persistence.mapper.IResultSetMapper;
import ro.msg.car.persistence.mapper.impl.JdbcResultSetMapper;
import ro.msg.car.service.ICarMigrationService;
import ro.msg.car.service.impl.CarMigrationService;
import ro.msg.car.service.impl.CarServiceLoggingDecorator;
import ro.msg.car.service.price.IPriceCalculatorFactory;
import ro.msg.car.service.price.impl.DefaultPriceCalculatorStrategy;
import ro.msg.car.service.price.impl.EpochPriceCalculatorStrategy;
import ro.msg.car.service.price.impl.LimousinePriceCalculatorStrategy;
import ro.msg.car.service.price.impl.PriceCalculatorFactory;
import ro.msg.car.service.price.impl.SUVPriceCalculatorStrategy;
import ro.msg.car.service.price.impl.SportPriceCalculatorStrategy;
import ro.msg.car.ui.ICarMigrationView;
import ro.msg.car.ui.controller.ICarMigrationController;
import ro.msg.car.ui.controller.impl.CarControllerLoggingDecorator;
import ro.msg.car.ui.controller.impl.SysoCarMigrationController;
import ro.msg.car.ui.impl.SysoCarMigrationView;
import ro.msg.car.ui.mapper.ICarMapper;
import ro.msg.car.ui.mapper.impl.SimpleCarMapper;
import ro.msg.car.ui.validator.IInputValidator;
import ro.msg.car.ui.validator.impl.FabricationYearInputValidator;
import ro.msg.car.ui.validator.impl.InputValidatorComposite;
import ro.msg.car.ui.validator.impl.LengthInputValidator;


public class CarMigration {
	
	public static void main(String[] args) {
		final ILogger logger = new SysoLogger();
		final JdbcCarMigrationPersistence persistence = new JdbcCarMigrationPersistence(logger);
		final IResultSetMapper rowMapper = new JdbcResultSetMapper();
		persistence.setRowMapper(rowMapper);
		final ICarMigrationPersistence logPersistence = new CarPersistenceLoggingDecorator(persistence, logger);
		final IPriceCalculatorFactory calculatorFactory = createPriceCalculatorFactory(logger);
		final ICarMigrationService service = new CarMigrationService(logPersistence, calculatorFactory, logger);
		final ICarMigrationService logService = new CarServiceLoggingDecorator(service, logger);
		final IInputValidator validator = createInputValidatorComposite(logger);
		final ICarMapper mapper = new SimpleCarMapper();
		final ICarMigrationController controller = new SysoCarMigrationController(logService, validator, mapper, logger);
		final ICarMigrationController logController = new CarControllerLoggingDecorator(controller, logger);
		
		final ICarMigrationView view = new SysoCarMigrationView(logController);
		view.start();
	}

	private static IInputValidator createInputValidatorComposite(final ILogger logger) {
		final InputValidatorComposite result = new InputValidatorComposite();
		result.addValidator(new LengthInputValidator(logger));
		result.addValidator(new FabricationYearInputValidator(logger));
		
		return result;
	}

	private static IPriceCalculatorFactory createPriceCalculatorFactory(final ILogger logger) {
		final IPriceService priceService = new PriceService();
		final IPriceCalculatorFactory result = new PriceCalculatorFactory(logger);
		result.addPriceCalculator(CarType.LIMOUSINE, new LimousinePriceCalculatorStrategy(priceService));
		result.addPriceCalculator(CarType.SPORT, new SportPriceCalculatorStrategy(priceService));
		result.addPriceCalculator(CarType.EPOCH, new EpochPriceCalculatorStrategy(priceService));
		result.addPriceCalculator(CarType.SUV, new SUVPriceCalculatorStrategy(priceService));
		result.setDefaultPriceCalculator(new DefaultPriceCalculatorStrategy(logger));
		
		return result;
	}

}
