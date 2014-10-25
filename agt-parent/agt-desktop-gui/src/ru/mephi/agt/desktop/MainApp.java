package ru.mephi.agt.desktop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt._interface.request.GuiRequest;
import ru.mephi.agt._interface.response.ContactListResponse;
import ru.mephi.agt._interface.response.MessageListResponse;
import ru.mephi.agt.desktop.constants.ViewPathConstant;
import ru.mephi.agt.desktop.converter.ContactConverter;
import ru.mephi.agt.desktop.model.ContactModel;
import ru.mephi.agt.desktop.util.ControllerUtil;
import ru.mephi.agt.desktop.util.ServerInteractor;
import ru.mephi.agt.desktop.view.ChatListController;
import ru.mephi.agt.desktop.view.ContactsController;
import ru.mephi.agt.desktop.view.LoginController;
import ru.mephi.agt.model.Contact;
import ru.mephi.agt.model.Message;
import ru.mephi.agt.model.Status;

public class MainApp extends Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);
	private Stage stage;
	private Stage dialogStage;
	private ChatListController chatListController;
	private ContactsController contactsController;

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

	private void initCheckMessagesThread() throws IOException {
		// Timeline timeline = new Timeline(new KeyFrame(2500, {} ->
		// updateAll());
		try {
			Timeline timeline = new Timeline(new KeyFrame(
					Duration.millis(2500), ae -> updateAll()));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			contactsController = loader.getController();
			contactsController.setMainApp(this);
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);
			stage.sizeToScene();
			stage.show();
			updateAll();
			initCheckMessagesThread();
			System.out.println("inited");
		} catch (IOException e) {
			LOGGER.error("Can't find {}", ViewPathConstant.LOGIN_VIEW_PATH, e);
		}
	}

	public void startChatWith(ContactModel contactModel, boolean showWindow) {

		try {
			if (dialogStage == null) {
				dialogStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class
						.getResource(ViewPathConstant.CHAT_LIST_VIEW_PATH));
				AnchorPane rootLayout = (AnchorPane) loader.load();
				chatListController = loader.getController();
				Scene scene = new Scene(rootLayout);
				dialogStage.setScene(scene);
			}

			if (!dialogStage.isShowing() && showWindow) {
				dialogStage.show();
			}

			chatListController.startChat(contactModel);
			LOGGER.info("Try start chat with: {}", contactModel);
		} catch (Exception e) {
			LOGGER.error("Can't sstart chat with: {}", contactModel);

		}
	}

	public void updateAll() {
		try {
			Map<Long, ContactModel> contacts = getContactList();
			List<Message> messages = getMessages();
			updateContactList(contacts, messages);
			updateChats(contacts, messages);
		} catch (Exception e) {
			LOGGER.error("Can't update all", e);
		}
	}

	private void updateContactList(Map<Long, ContactModel> contacts,
			List<Message> messages) {
		for (Message message : messages) {
			// Add to map
			ContactModel contact = contacts.get(message);
			if (contact == null) {
				contact = new ContactModel();
				contact.setDisplayName(message.getMessageSender() + "");
				contact.setIdProperty(message.getMessageSender());
				contact.setStatus(Status.UNKNOWN);
				contacts.put(message.getMessageSender(), contact);
			}
			contact.setAreNewMessages(true);
		}
		List<ContactModel> contactModels = new ArrayList<ContactModel>(
				contacts.values());
		contactsController.updateContactList(contactModels);
	}

	private void updateChats(Map<Long, ContactModel> contacts,
			List<Message> messages) throws IOException {
		for (Message message : messages) {
			ContactModel contact = contacts.get(message.getMessageSender());
			startChatWith(contact, false);
			chatListController.handleMessage(contact, message);
		}
	}

	private Map<Long, ContactModel> getContactList() {
		ContactListResponse response = ServerInteractor.getContacts();
		if (response != null) {
			// ONLINE
			List<Contact> onlineContacts = response.getOnlineContacts();
			List<ContactModel> onlineContactModels = ContactConverter
					.toGuiModelList(onlineContacts);
			for (ContactModel contactModel : onlineContactModels) {
				contactModel.setStatus(Status.ONLINE);
			}

			// OFFLINE
			List<Contact> offlineContacts = response.getOfflineContacts();
			List<ContactModel> offlineContactModels = ContactConverter
					.toGuiModelList(offlineContacts);
			for (ContactModel contactModel : offlineContactModels) {
				contactModel.setStatus(Status.OFFLINE);
			}

			// COMPOSE
			Map<Long, ContactModel> allMap = new HashMap<Long, ContactModel>();
			for (ContactModel contactModel : onlineContactModels) {
				allMap.put(contactModel.getIdProperty(), contactModel);
			}
			for (ContactModel contactModel : offlineContactModels) {
				allMap.put(contactModel.getIdProperty(), contactModel);
			}
			return allMap;
		}
		return null;
	}

	private List<Message> getMessages() {
		MessageListResponse response = ServerInteractor
				.getMessages(new GuiRequest());
		if (ControllerUtil.handleResponse(response)) {
			return response.getMessages();
		} else {
			// TODO
			return null;
		}
	}

	public void showInfoAbout(ContactModel contactModel) {
		LOGGER.info("Try show info about: {}", contactModel);
	}

	public Stage getStage() {
		return stage;
	}

}
