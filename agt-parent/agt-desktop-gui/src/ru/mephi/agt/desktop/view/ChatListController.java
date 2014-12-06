package ru.mephi.agt.desktop.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.AnchorPane;

import org.controlsfx.control.Notifications;

import ru.mephi.agt.desktop.MainApp;
import ru.mephi.agt.desktop.constants.ViewPathConstant;
import ru.mephi.agt.desktop.model.ContactModel;
import ru.mephi.agt.desktop.model.bean.MessageTabControllerBean;
import ru.mephi.agt.model.Message;

public class ChatListController {

	private MainApp mainApp;

	@FXML
	private TabPane chatList;
	private Map<Long, MessageTabControllerBean> messageTabControllerMap = new HashMap<Long, MessageTabControllerBean>();

	@FXML
	private void initialize() {
		chatList.tabClosingPolicyProperty().set(TabClosingPolicy.ALL_TABS);
	}

	public void startChat(ContactModel contactModel, boolean showWindow)
			throws IOException {
		if (messageTabControllerMap.containsKey(contactModel.getUserId())) {
			if (showWindow) {
				showOldChat(contactModel);
			}
		} else {
			startNewChat(contactModel, showWindow);
		}
	}

	private void startNewChat(ContactModel contactModel, boolean showWindow)
			throws IOException {
		Tab newTab = new Tab(contactModel.getDisplayName());
		newTab.setUserData(contactModel);
		newTab.setClosable(true);
		newTab.onClosedProperty().set(e -> closeTab(e));
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class
				.getResource(ViewPathConstant.MESSAGES_VIEW_PATH));
		AnchorPane rootLayout = (AnchorPane) loader.load();
		newTab.setContent(rootLayout);
		chatList.getTabs().add(newTab);
		if (showWindow) {
			chatList.getSelectionModel().select(newTab);
		}
		MessagesController messagesController = loader.getController();
		messagesController.setContact(contactModel);
		messagesController.setMainApp(mainApp);
		messageTabControllerMap.put(contactModel.getUserId(),
				new MessageTabControllerBean(newTab, messagesController));
	}

	private void closeTab(Event e) {
		Tab tab = (Tab) e.getSource();
		ContactModel contactModel = (ContactModel) tab.getUserData();
		messageTabControllerMap.remove(contactModel.getUserId());
	}

	private void showOldChat(ContactModel contactModel) {
		Tab tab = messageTabControllerMap.get(contactModel.getUserId()).getTab();
		chatList.getSelectionModel().select(tab);
	}

	public void handleMessage(ContactModel contactModel, Message message)
			throws IOException {
		if (!messageTabControllerMap.containsKey(contactModel.getUserId())) {
			startNewChat(contactModel, false);
		}
		MessageTabControllerBean messageTabControllerBean = messageTabControllerMap
				.get(contactModel.getUserId());
		MessagesController messageController = messageTabControllerBean
				.getController();
		messageController.handleMessage(message.getMessageTime(),
				message.getMessage());
		Notifications.create().title(contactModel.getDisplayName())
				.text(message.getMessage())
				.onAction(ae -> mainApp.startChatWith(contactModel, true))
				.showInformation();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
