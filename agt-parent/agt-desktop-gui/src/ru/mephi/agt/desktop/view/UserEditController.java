package ru.mephi.agt.desktop.view;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.mephi.agt.desktop.model.converter.GenderConverter;
import ru.mephi.agt.model.Gender;
import ru.mephi.agt.model.User;

public class UserEditController {

	@FXML
	private Label idLabel;
	@FXML
	private TextField nickTextField;
	@FXML
	private TextField firstNameTextField;
	@FXML
	private TextField secondNameTextField;
	@FXML
	private DatePicker birthTextField;
	@FXML
	private TextField cityTextField;
	@FXML
	private TextField countryTextField;
	@FXML
	private ComboBox<Gender> genderField;

	@FXML
	private void initialize() {
		genderField.getItems().addAll(null, Gender.MALE, Gender.FEMALE,
				Gender.SHEMALE, Gender.EMPTY);
		genderField.setConverter(new GenderConverter());
	}

	public void setUser(User user) {
		idLabel.setText(user.getUserId() + "");
		nickTextField.setText(user.getNickName());
		firstNameTextField.setText(user.getFirstName());
		secondNameTextField.setText(user.getLastName());
		LocalDate birthDate = user.getBirthDate() == null ? null : user
				.getBirthDate().toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
		birthTextField.setValue(birthDate);
		cityTextField.setText(user.getCity());
		countryTextField.setText(user.getCountry());
		genderField.setValue(user.getGender());
	}

	public User getUser() {
		User user = new User();

		user.setUserId(Long.parseLong(idLabel.getText()));
		user.setNickName(nickTextField.getText());
		user.setFirstName(firstNameTextField.getText());
		user.setLastName(secondNameTextField.getText());
		if (birthTextField.getValue() != null) {
			Instant instant = birthTextField.getValue().atStartOfDay()
					.atZone(ZoneId.systemDefault()).toInstant();
			Date birthDate = Date.from(instant);
			user.setBirthDate(birthDate);
		}
		user.setCity(cityTextField.getText());
		user.setCountry(countryTextField.getText());
		user.setGender(genderField.getValue());

		return user;
	}
}
