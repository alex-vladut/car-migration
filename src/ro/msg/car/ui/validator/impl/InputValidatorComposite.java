package ro.msg.car.ui.validator.impl;

import java.util.ArrayList;
import java.util.List;

import ro.msg.car.ui.validator.IInputValidator;

public class InputValidatorComposite implements IInputValidator {
	private final List<IInputValidator> validators = new ArrayList<>();
	
	@Override
	public boolean isValid(final String[] values) {
		for(final IInputValidator validator : this.validators) {
			if(!validator.isValid(values)) {
				return false;
			}
		}
		
		return true;
	}
	
	public void addValidator(final IInputValidator validator) {
		this.validators.add(validator);
	}
	
	public void removeValidator(final IInputValidator validator) {
		this.validators.remove(validator);
	}
}
