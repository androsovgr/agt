package ru.mephi.agt.desktop.view;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.util.Callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt._interface.response.ContactListResponse;
import ru.mephi.agt.desktop.MainApp;
import ru.mephi.agt.desktop.component.ContactListCell;
import ru.mephi.agt.desktop.converter.ContactConverter;
import ru.mephi.agt.desktop.model.ContactModel;
import ru.mephi.agt.desktop.util.ServerInteractor;
import ru.mephi.agt.model.Contact;
import ru.mephi.agt.model.Status;

public class ContactsController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ContactsController.class);

	@FXML
	private ListView<ContactModel> onlineList;

	@FXML
	private ListView<ContactModel> offlineList;

	@FXML
	private ListView<ContactModel> unknownList;

	@FXML
	private void initialize() {
		updateContactList();
	}

	private MainApp mainApp;

	private void updateContactList() {
		ContactListResponse response = ServerInteractor.getContacts();
		if (response != null) {
			// ONLINE
			List<Contact> onlineContacts = response.getOnlineContacts();
			List<ContactModel> onlineContactModels = ContactConverter
					.toGuiModelList(onlineContacts);
			for (ContactModel contactModel : onlineContactModels) {
				contactModel.setStatus(Status.ONLINE);
			}
			onlineList.setItems(FXCollections
					.observableArrayList(onlineContactModels));
			onlineList.getSelectionModel().setSelectionMode(
					SelectionMode.SINGLE);
			onlineList
					.setCellFactory(new Callback<ListView<ContactModel>, ListCell<ContactModel>>() {
						@Override
						public ListCell<ContactModel> call(
								ListView<ContactModel> param) {
							return new ContactListCell();
						}
					});
			// OFFLINE
			List<Contact> offlineContacts = response.getOfflineContacts();
			List<ContactModel> offlineContactModels = ContactConverter
					.toGuiModelList(offlineContacts);
			for (ContactModel contactModel : offlineContactModels) {
				contactModel.setStatus(Status.OFFLINE);
			}
			offlineList.setItems(FXCollections
					.observableArrayList(offlineContactModels));
			offlineList
					.setCellFactory(new Callback<ListView<ContactModel>, ListCell<ContactModel>>() {
						@Override
						public ListCell<ContactModel> call(
								ListView<ContactModel> param) {
							return new ContactListCell();
						}
					});
		}
	}

	@FXML
	private void startChat(ActionEvent event) {
		ContactModel selected = getSelected();
		if (selected != null) {
			mainApp.startChatWith(selected);
		} else {
			LOGGER.warn("Can't find selected item");
		}
	}

	@FXML
	private void showInfo(ActionEvent event) {
		ContactModel selected = getSelected();
		if (selected != null) {
			mainApp.showInfoAbout(selected);
		} else {
			LOGGER.warn("Can't find selected item");
		}
	}

	private ContactModel getSelected() {
		ContactModel selected = onlineList.getSelectionModel()
				.getSelectedItem();
		if (selected == null) {
			selected = offlineList.getSelectionModel().getSelectedItem();
		}
		if (selected == null) {
			// TODO
			// selected = unknownList.getSelectionModel().getSelectedItem();
		}
		return selected;
	}

	private void dropOtherSelection(ListView<ContactModel> listView) {
		if (offlineList != listView) {
			offlineList.getSelectionModel().select(-1);
		}
		if (onlineList != listView) {
			onlineList.getSelectionModel().select(-1);
		}
		if (unknownList != listView) {
			unknownList.getSelectionModel().select(-1);
		}
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}
