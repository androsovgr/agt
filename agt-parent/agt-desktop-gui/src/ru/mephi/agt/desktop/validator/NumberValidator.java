package ru.mephi.agt.desktop.validator;

import jidefx.scene.control.validation.ValidationEvent;
import jidefx.scene.control.validation.ValidationObject;
import jidefx.scene.control.validation.Validator;

public class NumberValidator implements Validator {

	private Long minValue;
	private Long maxValue;

	private String emptyValueMessage = "1";// "¬ведите непустое значение";
	private String notNumberMessage = "2";// ¬веденное значение не €вл€етс€
											// числом";
	private String outOfRangeMessage = "3";// ¬ведено значение, выход€щее за
											// допустимый диапазон";

	public NumberValidator() {
		super();
	}

	public NumberValidator(Long minValue, Long maxValue) {
		super();
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	@Override
	public ValidationEvent call(ValidationObject param) {
		if (param.getNewValue() == null
				|| param.getNewValue().toString().isEmpty()) {
			return new ValidationEvent(ValidationEvent.VALIDATION_ERROR, 1,
					emptyValueMessage);
		}
		Long newValue = null;
		try {
			newValue = Long.parseLong(param.getNewValue().toString());
		} catch (NumberFormatException e) {
		}
		if (newValue == null) {
			return new ValidationEvent(ValidationEvent.VALIDATION_ERROR, 2,
					notNumberMessage);
		}
		if (maxValue != null && maxValue < newValue || minValue != null
				&& minValue > newValue) {
			return new ValidationEvent(ValidationEvent.VALIDATION_ERROR, 3,
					outOfRangeMessage);
		}
		return ValidationEvent.OK;
	}
}
