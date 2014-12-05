package ru.mephi.agt.desktop.model.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.util.StringConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateConverter extends StringConverter<Date> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DateConverter.class);

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String toString(Date object) {
		if (object != null) {
			return dateFormat.format(object);
		} else {
			return null;
		}
	}

	@Override
	public Date fromString(String string) {
		Date date = null;
		try {
			date = dateFormat.parse(string);
		} catch (Exception e) {
			LOGGER.warn("Can't parse date: {}", string);
		}
		return date;
	}

}
