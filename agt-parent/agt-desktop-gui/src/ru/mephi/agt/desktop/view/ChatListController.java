package ru.mephi.agt.desktop.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import ru.mephi.agt.desktop.MainApp;
import ru.mephi.agt.desktop.constants.ViewPathConstant;
import ru.mephi.agt.desktop.model.ContactModel;
import ru.mephi.agt.model.Message;

public class ChatListController {

	@FXML
	private TabPane chatList;
	private List<ContactModel> chatReference = new ArrayList<ContactModel>();
	private Map<Long, MessagesController> messageControllerMap = new HashMap<Long, MessagesController>();

	@FXML
	private void initialize() {
	}

	public void startChat(ContactModel contactModel) throws IOException {
		int index = chatReference.indexOf(contactModel);
		if (index < 0) {
			startNewChat(contactModel);
		} else {
			showOldChat(contactModel);
		}
	}

	private void startNewChat(ContactModel contactModel) throws IOException {
		Tab newTab = new Tab(contactModel.getDisplayName());
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class
				.getResource(ViewPathConstant.MESSAGES_VIEW_PATH));
		AnchorPane rootLayout = (AnchorPane) loader.load();
		newTab.setContent(rootLayout);
		chatList.getTabs().add(newTab);
		chatList.getSelectionModel().select(newTab);
		chatReference.add(contactModel);
		//
		MessagesController messagesController = loader.getController();
		messagesController.setContact(contactModel);
		messageControllerMap.put(contactModel.getIdProperty(),
				messagesController);
	}

	private void showOldChat(ContactModel contactModel) {
		int index = chatReference.indexOf(contactModel);
		chatList.getSelectionModel().select(index);
	}

	public void handleMessage(ContactModel contactModel, Message message)
			throws IOException {
		MessagesController messageController = messageControllerMap
				.get(contactModel.getIdProperty());
		if (messageController == null) {
			startNewChat(contactModel);
			messageController = messageControllerMap.get(contactModel
					.getIdProperty());
		}
		messageController.handleMessage(message.getMessageTime(),
				message.getMessage());
	}

}
