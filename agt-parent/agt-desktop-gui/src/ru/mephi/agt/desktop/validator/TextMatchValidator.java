package ru.mephi.agt.desktop.validator;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextInputControl;
import jidefx.scene.control.validation.ValidationEvent;
import jidefx.scene.control.validation.ValidationObject;
import jidefx.scene.control.validation.Validator;

public class TextMatchValidator implements Validator {

	private TextInputControl textInput;
	PasswordField f;

	@Override
	public ValidationEvent call(ValidationObject param) {
		if (param.getNewValue() != null
				&& param.getNewValue().equals(textInput.getText())) {
			return ValidationEvent.OK;
		}
		return new ValidationEvent(ValidationEvent.VALIDATION_ERROR, 1,
				"Пароли не совпадают");

	}
}