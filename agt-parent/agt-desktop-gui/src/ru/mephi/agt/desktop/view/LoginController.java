package ru.mephi.agt.desktop.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.desktop.MainApp;
import ru.mephi.agt.desktop.util.ServerInteractor;

public class LoginController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoginController.class);

	@FXML
	private TextField idField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Hyperlink registerHyperlink;
	@FXML
	private Button loginButton;
	@FXML
	private PasswordField registerPasswordField;
	@FXML
	private PasswordField confirmRegisterPasswordField;
	@FXML
	private Button registerButton;
	@FXML
	private GridPane registerPane;

	private boolean registerPaneShown;

	// Reference to the main application.
	private MainApp mainApp;

	@FXML
	private void initialize() {
		registerPane.setVisible(false);
		registerPane.setManaged(false);
	}

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public LoginController() {
		super();
	}

	@FXML
	private void login() {
		String login = idField.getText();
		String password = passwordField.getText();
		if (login == null || login.isEmpty() || !login.matches("\\d{1,15}")) {
			LOGGER.info("Incorrect login: {}", login);
		} else if (password == null || password.isEmpty()) {
			LOGGER.info("Incorrect password: {}", password);
		} else {
			ServerInteractor.login(login, password);
			// TODO: if logined
			mainApp.initContacts();	
		}
	}

	@FXML
	private void registerPaneSwitch() {
		registerPaneShown = !registerPaneShown;
		registerPane.setVisible(registerPaneShown);
		registerPane.setManaged(registerPaneShown);
		mainApp.getStage().sizeToScene();
		if (registerPaneShown) {
			registerHyperlink.setText("Не регистрироваться");
		} else {
			registerHyperlink.setText("Зарегистрироваться");
		}
	}

	@FXML
	private void register() {
		String passwordFirst = registerPasswordField.getText();
		String passwordSecond = confirmRegisterPasswordField.getText();
		if (passwordFirst != null && passwordSecond != null
				&& passwordFirst.equals(passwordSecond)
				&& !passwordFirst.isEmpty()) {
			ServerInteractor.register(passwordFirst);
		}
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}
