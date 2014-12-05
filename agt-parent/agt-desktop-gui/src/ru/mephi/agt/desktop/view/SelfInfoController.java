package ru.mephi.agt.desktop.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.desktop.MainApp;
import ru.mephi.agt.desktop.constants.ViewPathConstant;
import ru.mephi.agt.desktop.util.ControllerUtil;
import ru.mephi.agt.desktop.util.ServerInteractor;
import ru.mephi.agt.model.User;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.UserResponse;

public class SelfInfoController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SelfInfoController.class);

	private MainApp mainApp;

	private UserInfoController infoController;
	private GridPane infoPain;
	private UserEditController editController;
	private GridPane editPain;

	@FXML
	private Button saveButton;
	@FXML
	private Button cancelButton;
	@FXML
	private Button editButton;
	@FXML
	private AnchorPane pane;

	private User user;

	@FXML
	private void initialize() {
		saveButton.disableProperty().set(true);
		cancelButton.disableProperty().set(true);
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(MainApp.class
					.getResource(ViewPathConstant.USER_INFO_VIEW_PATH));
			infoPain = (GridPane) loader.load();
			infoController = loader.getController();
		} catch (IOException e) {
			LOGGER.error("Can't load {}", ViewPathConstant.USER_INFO_VIEW_PATH,
					e);
		}
		try {
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource(ViewPathConstant.USER_EDIT_VIEW_PATH));
			editPain = (GridPane) loader.load();
			editController = loader.getController();
		} catch (IOException e) {
			LOGGER.error("Can't load {}", ViewPathConstant.USER_EDIT_VIEW_PATH,
					e);
		}
		pane.getChildren().setAll(infoPain);
	}

	@FXML
	private void startEdit() {
		editButton.disableProperty().set(true);
		saveButton.disableProperty().set(false);
		cancelButton.disableProperty().set(false);
		editController.setUser(user);
		pane.getChildren().setAll(editPain);
	}

	@FXML
	private void cancel() {
		editButton.disableProperty().set(false);
		saveButton.disableProperty().set(true);
		cancelButton.disableProperty().set(true);
		pane.getChildren().setAll(infoPain);
	}

	@FXML
	private void save() {
		saveButton.disableProperty().set(true);
		cancelButton.disableProperty().set(true);
		user = editController.getUser();
		BaseResponse response = ServerInteractor.updateOwnInfo(user,
				mainApp.getUid(), mainApp.getOwnId());
		if (ControllerUtil.handleResponse(response)) {
			infoController.setUser(user);
			pane.getChildren().setAll(infoPain);
			editButton.disableProperty().set(false);
		}

	}

	public void loadSelfInfo() {
		UserResponse response = ServerInteractor.getUser(mainApp.getOwnId(),
				mainApp.getUid(), mainApp.getOwnId());
		if (ControllerUtil.handleResponse(response)) {
			user = response.getUser();
			infoController.setUser(user);
			editController.setUser(user);
		}

	}

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}
