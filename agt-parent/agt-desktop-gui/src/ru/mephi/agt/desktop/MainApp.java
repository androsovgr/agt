package ru.mephi.agt.desktop;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.desktop.constants.ViewPathConstant;
import ru.mephi.agt.desktop.model.ContactModel;
import ru.mephi.agt.desktop.view.ContactsController;
import ru.mephi.agt.desktop.view.LoginController;

public class MainApp extends Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);
	private Stage stage;
	private Stage dialogStage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
		stage.setTitle("AGT-messenger");
		primaryStage.getIcons().add(
				new Image("file:resources/images/address_book_32.png"));
		initLoginDialog();
	}

	private void initLoginDialog() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource(ViewPathConstant.LOGIN_VIEW_PATH));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			LoginController loginController = loader.getController();
			loginController.setMainApp(this);
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.sizeToScene();
			stage.show();
		} catch (IOException e) {
			LOGGER.error("Can't find {}", ViewPathConstant.LOGIN_VIEW_PATH, e);
		}
	}

	public void initContacts() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource(ViewPathConstant.CONTACTS_VIEW_PATH));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			ContactsController contactsController = loader.getController();
			contactsController.setMainApp(this);
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);
			stage.sizeToScene();
			stage.show();
			// contactsController.tuneTable();
		} catch (IOException e) {
			LOGGER.error("Can't find {}", ViewPathConstant.LOGIN_VIEW_PATH, e);
		}
	}

	public void startChatWith(ContactModel contactModel) {
		dialogStage = new Stage();
		Scene scene = new Scene(new AnchorPane());
		dialogStage.setScene(scene);
		dialogStage.show();
		LOGGER.info("Try start chat with: {}", contactModel);
	}

	public void showInfoAbout(ContactModel contactModel) {
		LOGGER.info("Try show info about: {}", contactModel);
	}

	public Stage getStage() {
		return stage;
	}

}
