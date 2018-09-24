package ro.msg.car.ui.controller.impl;

import java.util.List;

import ro.msg.car.logger.ILogger;
import ro.msg.car.ui.controller.ICarMigrationController;

public class CarControllerLoggingDecorator implements ICarMigrationController {
	
	private final ICarMigrationController controller;
	private final ILogger logger;
	
	public CarControllerLoggingDecorator(
			final ICarMigrationController controller,
			final ILogger logger) {
		this.controller = controller;
		this.logger = logger;
	}
	
	@Override
	public void saveInputEntries(final List<String[]> lines) {
		logger.logDebug("> CarController.saveInputEntries(List<String[]>)");
		final long start = System.currentTimeMillis();
		
		controller.saveInputEntries(lines);
		
		final long end = System.currentTimeMillis();
		logger.logDebug("< CarController.saveInputEntries(List<String>) executed in %d ms.", (end-start));
	}

	@Override
	public List<String[]> findAllMigratedEntries() {
		logger.logDebug("> CarController.findAllMigratedEntries()");
		final long start = System.currentTimeMillis();
		
		final List<String[]> result = controller.findAllMigratedEntries();
		
		final long end = System.currentTimeMillis();
		logger.logDebug("< CarController.findAllMigratedEntries() executed in %d ms.", (end-start));
		
		return result;
	}

}
