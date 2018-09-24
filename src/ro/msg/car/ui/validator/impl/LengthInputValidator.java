package ro.msg.car.ui.validator.impl;

import ro.msg.car.logger.ILogger;
import ro.msg.car.ui.validator.IInputValidator;

public class LengthInputValidator implements IInputValidator {
	private final ILogger logger;
	
	public LengthInputValidator(final ILogger logger) {
		this.logger = logger;
	}
	
	@Override
	public boolean isValid(final String[] values) {
		if(values.length != 5) {
			logger.logWarning("The input line should have 5 elements!");
			return false;
		}else {
			return true;
		}
	}

}
