package ru.mephi.agt.desktop.view;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import org.controlsfx.control.Notifications;
import org.controlsfx.dialog.Dialogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.desktop.MainApp;
import ru.mephi.agt.desktop.constants.ViewPathConstant;
import ru.mephi.agt.desktop.util.ControllerUtil;
import ru.mephi.agt.desktop.util.ServerInteractor;
import ru.mephi.agt.model.User;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.UserResponse;

public class OtherInfoController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SelfInfoController.class);

	private MainApp mainApp;
	private Stage stage;

	private UserInfoController infoController;
	private User user;

	public void select(long userId) {
		UserResponse response = ServerInteractor.getUser(userId,
				mainApp.getUid(), mainApp.getOwnId());
		if (ControllerUtil.handleResponse(response)) {
			user = response.getUser();
			infoController.setUser(user);
		}
	}

	@FXML
	private AnchorPane pane;

	@FXML
	private void initialize() {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(MainApp.class
					.getResource(ViewPathConstant.USER_INFO_VIEW_PATH));
			GridPane infoPain = (GridPane) loader.load();
			pane.getChildren().setAll(infoPain);
			infoController = loader.getController();
		} catch (IOException e) {
			LOGGER.error("Can't load {}", ViewPathConstant.USER_INFO_VIEW_PATH,
					e);
		}

	}

	@FXML
	private void startChat() {
		if (user != null) {
			mainApp.startChatWith(user.getUserId());
		}
	}

	@FXML
	private void addToContactList() {
		if (user != null) {
			Optional<String> dialogResponse = Dialogs.create().owner(stage)
					.title("Добавление нового контакта")
					.message("Введите желаемое имя контакта:")
					.showTextInput(user.getNickName());
			if (dialogResponse.isPresent()) {
				String displayName = dialogResponse.get();
				BaseResponse response = ServerInteractor.addContact(
						displayName, user.getUserId(), mainApp.getUid(),
						mainApp.getOwnId());
				if (ControllerUtil.handleResponse(response)) {
					Notifications
							.create()
							.title("Добавление пользователя")
							.text("Пользователь " + displayName
									+ " успешно добавлен в контакт-лист")
							.showInformation();
					mainApp.updateContactMapPutIntoContacts();
				}
			}

		}
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
