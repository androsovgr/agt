package ru.mephi.agt.desktop.model.converter;

import javafx.util.StringConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.desktop.constants.GenderLabel;
import ru.mephi.agt.model.Gender;

public class GenderConverter extends StringConverter<Gender> {

	private static final Logger Logger = LoggerFactory
			.getLogger(GenderConverter.class);

	@Override
	public String toString(Gender gender) {
		if (gender == null) {
			return GenderLabel.ANY;
		} else if (gender == Gender.MALE) {
			return GenderLabel.MALE;
		} else if (gender == Gender.FEMALE) {
			return GenderLabel.FEMALE;
		} else if (gender == Gender.EMPTY) {
			return GenderLabel.EMPTY;
		} else if (gender == Gender.SHEMALE) {
			return GenderLabel.SHEMALE;
		} else {
			Logger.warn("Unknown gender: {}", gender);
			return null;
		}
	}

	@Override
	public Gender fromString(String gender) {
		if (gender.equals(GenderLabel.ANY)) {
			return null;
		} else if (gender.equals(GenderLabel.MALE)) {
			return Gender.MALE;
		} else if (gender.equals(GenderLabel.FEMALE)) {
			return Gender.FEMALE;
		} else if (gender.equals(GenderLabel.EMPTY)) {
			return Gender.EMPTY;
		} else if (gender.equals(GenderLabel.SHEMALE)) {
			return Gender.SHEMALE;
		} else {
			Logger.warn("Unknown gender: {}", gender);
			return null;
		}
	}

}
