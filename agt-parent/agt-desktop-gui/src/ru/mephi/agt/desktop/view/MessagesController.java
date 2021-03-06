package ru.mephi.agt.desktop.view;

import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.desktop.MainApp;
import ru.mephi.agt.desktop.constants.StyleClassContstant;
import ru.mephi.agt.desktop.model.ContactModel;
import ru.mephi.agt.desktop.util.ControllerUtil;
import ru.mephi.agt.desktop.util.ServerInteractor;
import ru.mephi.agt.response.BaseResponse;

public class MessagesController {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MessagesController.class);

	private MainApp mainApp;

	private ContactModel contact;

	@FXML
	private TextFlow chat;
	@FXML
	private TextArea message;
	@FXML
	private ScrollPane messagePane;

	@FXML
	private void initialize() {

	}

	@FXML
	private void sendMessage() {
		BaseResponse response = ServerInteractor.sendMessage(contact,
				message.getText(), mainApp.getOwnId(), mainApp.getUid());
		if (ControllerUtil.handleResponse(response)) {
			addToChat("�", new Date(), message.getText());
			message.clear();
			messagePane.setVvalue(Double.MAX_VALUE);
		} else {
			// TODO
		}
	}

	private void addToChat(String sender, Date time, String message) {
		Text messageHeader = new Text(sender + " (" + time + "):");
		messageHeader.getStyleClass().add(
				StyleClassContstant.MESSAGE_HEADER_CLASS);
		Text messageBody = new Text(message + "\n");
		messageBody.getStyleClass().add(StyleClassContstant.MESSAGE_BODY_CLASS);
		chat.getChildren().addAll(messageHeader, messageBody);
	}

	public void handleMessage(Date time, String message) {
		addToChat(contact.getDisplayName(), time, message);
	}

	public void setContact(ContactModel contact) {
		this.contact = contact;
	}

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}
