package ru.mephi.agt.desktop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import ru.mephi.agt.api.response.MessageListResponse;
import ru.mephi.agt.desktop.constants.ViewPathConstant;
import ru.mephi.agt.desktop.converter.ContactConverter;
import ru.mephi.agt.desktop.model.ContactModel;
import ru.mephi.agt.desktop.model.UserModel;
import ru.mephi.agt.desktop.util.ControllerUtil;
import ru.mephi.agt.desktop.util.ServerInteractor;
import ru.mephi.agt.desktop.view.ChatListController;
import ru.mephi.agt.desktop.view.ContactsController;
import ru.mephi.agt.desktop.view.LoginController;
import ru.mephi.agt.desktop.view.SearchController;
import ru.mephi.agt.model.Contact;
import ru.mephi.agt.model.Message;
import ru.mephi.agt.model.Status;
import ru.mephi.agt.request.gui.GuiRequest;
import ru.mephi.agt.response.ContactListResponse;
import ru.mephi.agt.response.IdListResponse;

public class MainApp extends Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);
	private Stage stage;
	private Stage dialogStage;
	private Stage searchStage;
	private ChatListController chatListController;
	private ContactsController contactsController;
	private String uid;
	private long ownId;
	private HashMap<Long, ContactModel> contactMap = new HashMap<Long, ContactModel>();
	private List<Contact> contacts;

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
			updateContactMapPutIntoContacts();
			initCheckMessagesThread();
		} catch (IOException e) {
			LOGGER.error("Can't find {}", ViewPathConstant.LOGIN_VIEW_PATH, e);
		}
	}

	public void showSearchStage() {
		try {
			// Load root layout from fxml file.
			if (searchStage == null) {
				searchStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class
						.getResource(ViewPathConstant.SEARCH_VIEW_PATH));
				AnchorPane rootLayout = (AnchorPane) loader.load();
				SearchController searchController = loader.getController();
				searchController.setMainApp(this);
				searchController.setStage(searchStage);
				// Show the scene containing the root layout.
				Scene scene = new Scene(rootLayout);
				searchStage.setScene(scene);
				searchStage.sizeToScene();
			}
			searchStage.show();
		} catch (IOException e) {
			LOGGER.error("Can't find {}", ViewPathConstant.SEARCH_VIEW_PATH, e);
		}
	}

	public void startChatWith(UserModel userModel) {
		ContactModel contactModel = contactMap.get(userModel.getId());
		if (contactModel == null) {
			contactModel = createEmptyContactById(userModel.getId());
		}
		startChatWith(contactModel, true);
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
				chatListController.setMainApp(this);
				Scene scene = new Scene(rootLayout);
				dialogStage.setScene(scene);
			}

			if (showWindow) {
				contactModel.setNewMessages(false);
			}

			if (!dialogStage.isShowing() && showWindow) {
				dialogStage.show();
			}

			chatListController.startChat(contactModel, showWindow);
			LOGGER.debug("Try start chat with: {}", contactModel);
		} catch (Exception e) {
			LOGGER.error("Can't sstart chat with: {}", contactModel);

		}
	}

	public void updateAll() {
		try {
			List<Message> messages = getMessages();
			updateContactStatuses();
			List<ContactModel> delta = addReceivedMessagesForContactMap(messages);
			contactsController.updateContactList(delta);
			updateChats(messages);
		} catch (Exception e) {
			LOGGER.error("Can't update all", e);
		}
	}

	private List<ContactModel> addReceivedMessagesForContactMap(
			List<Message> messages) {
		List<ContactModel> delta = new ArrayList<ContactModel>();
		for (Message message : messages) {
			// Add to map
			ContactModel contact = contactMap.get(message.getMessageSender());
			if (contact == null) {
				contact = createEmptyContactById(message.getMessageSender());
				delta.add(contact);
			}
			contact.setNewMessages(true);
		}
		return delta;
	}

	private ContactModel createEmptyContactById(long id) {
		ContactModel contact = new ContactModel();
		contact.setDisplayName(id + "");
		contact.setIdProperty(id);
		contact.setStatus(Status.UNKNOWN);
		contactMap.put(id, contact);
		return contact;
	}

	private void updateChats(List<Message> messages) throws IOException {
		for (Message message : messages) {
			ContactModel contact = contactMap.get(message.getMessageSender());
			startChatWith(contact, false);
			chatListController.handleMessage(contact, message);
		}
	}

	public void updateContactMapPutIntoContacts() {
		List<ContactModel> delta = new ArrayList<ContactModel>();
		ContactListResponse response = ServerInteractor.getContacts(ownId, uid);
		if (ControllerUtil.handleResponse(response)) {
			contacts = response.getContacts();
			List<Contact> contacts = response.getContacts();
			for (Contact contact : contacts) {
				if (!contactMap.containsKey(contact.getUser().getUserId())) {
					ContactModel guiContact = ContactConverter
							.toGuiModel(contact);
					guiContact.setStatus(Status.OFFLINE);
					delta.add(guiContact);
					contactMap.put(guiContact.getId(), guiContact);
				}
			}
		}
		contactsController.updateContactList(delta);
	}

	private void updateContactStatuses() {
		IdListResponse response = ServerInteractor.getOnlineOf(contacts, uid,
				ownId);
		ServerInteractor.getOnlineOf(contacts, uid, ownId);
		if (response != null) {
			Set<Long> onlineSet = new HashSet<Long>();
			onlineSet.addAll(response.getIdList());
			List<Long> onlineContactIds = response.getIdList();
			List<Long> offlineContactIds = new ArrayList<Long>();
			for (Contact contact : contacts) {
				if (!onlineSet.contains(contact.getUser().getUserId())) {
					offlineContactIds.add(contact.getUser().getUserId());
				}
			}
			// UPDATE
			for (Long contactId : onlineContactIds) {
				ContactModel contact = contactMap.get(contactId);
				if (Status.ONLINE != contact.getStatus()) {
					contact.setStatus(Status.ONLINE);
				}
			}
			for (Long contactId : offlineContactIds) {
				ContactModel contact = contactMap.get(contactId);
				if (Status.OFFLINE != contact.getStatus()) {
					contact.setStatus(Status.OFFLINE);
				}
			}
		}
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public long getOwnId() {
		return ownId;
	}

	public void setOwnId(long ownId) {
		this.ownId = ownId;
	}

}
