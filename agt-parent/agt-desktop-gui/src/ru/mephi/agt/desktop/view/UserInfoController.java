package ru.mephi.agt.desktop.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ru.mephi.agt.desktop.model.converter.DateConverter;
import ru.mephi.agt.desktop.model.converter.GenderConverter;
import ru.mephi.agt.model.User;

public class UserInfoController {

	@FXML
	private Label idLabel;
	@FXML
	private Label nickLabel;
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label secondNameLabel;
	@FXML
	private Label birthLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label countryLabel;
	@FXML
	private Label genderLabel;

	private final GenderConverter genderConverter = new GenderConverter();
	private final DateConverter dateConverter = new DateConverter();

	@FXML
	private void initialize() {
	}

	public void setUser(User user) {
		idLabel.setText(user.getUserId() + "");
		nickLabel.setText(user.getNickName());
		firstNameLabel.setText(user.getFirstName());
		secondNameLabel.setText(user.getLastName());
		birthLabel.setText(dateConverter.toString(user.getBirthDate()));
		cityLabel.setText(user.getCity());
		countryLabel.setText(user.getCountry());
		genderLabel.setText(genderConverter.toString(user.getGender()));
	}

}
