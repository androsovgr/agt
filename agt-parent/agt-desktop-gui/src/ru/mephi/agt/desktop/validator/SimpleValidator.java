package ru.mephi.agt.desktop.validator;

import jidefx.scene.control.validation.ValidationEvent;
import jidefx.scene.control.validation.ValidationObject;
import jidefx.scene.control.validation.Validator;

public class SimpleValidator implements Validator {

	@Override
	public ValidationEvent call(ValidationObject param) {
		if (param.getNewValue() != null
				&& !param.getNewValue().toString().isEmpty()) {
			return ValidationEvent.OK;
		} else {
			return new ValidationEvent(ValidationEvent.VALIDATION_ERROR, 1,
					"Error");
		}
	}
}
