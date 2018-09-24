package ro.msg.car.ui.validator.impl;

import ro.msg.car.logger.ILogger;
import ro.msg.car.ui.validator.IInputValidator;

public class FabricationYearInputValidator implements IInputValidator {
	private final ILogger logger;
	
	public FabricationYearInputValidator(final ILogger logger) {
		this.logger = logger;
	}
	
	@Override
	public boolean isValid(final String[] values) {
		if(!isNumber(values[2].trim())) {
			logger.logWarning("The second value (fabrication year) should be a number!");
			return false;
		}else {
			return true;
		}
	}
	
	private boolean isNumber(final String value) {
		if(value != null && value.matches("[0-9]{4}")) {
			return true;
		}
		return false;
	}

}
