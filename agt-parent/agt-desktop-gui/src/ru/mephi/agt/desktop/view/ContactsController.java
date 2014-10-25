package ru.mephi.agt.desktop.view;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.util.Callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.desktop.MainApp;
import ru.mephi.agt.desktop.component.ContactListCell;
import ru.mephi.agt.desktop.model.ContactModel;
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
		addHandlersForLists();
	}

	private MainApp mainApp;

	public void updateContactList(List<ContactModel> contactModels) {
		List<ContactModel> onlineContactModels = new ArrayList<ContactModel>();
		for (ContactModel contactModel : contactModels) {
			if (contactModel.getStatus().equals(Status.ONLINE)) {
				onlineContactModels.add(contactModel);
			}
		}
		onlineList.setItems(FXCollections
				.observableArrayList(onlineContactModels));
		onlineList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		onlineList
				.setCellFactory(new Callback<ListView<ContactModel>, ListCell<ContactModel>>() {
					@Override
					public ListCell<ContactModel> call(
							ListView<ContactModel> param) {
						return new ContactListCell();
					}
				});
		// OFFLINE

		List<ContactModel> offlineContactModels = new ArrayList<ContactModel>();
		for (ContactModel contactModel : contactModels) {
			if (contactModel.getStatus().equals(Status.OFFLINE)) {
				offlineContactModels.add(contactModel);
			}
		}
		offlineList.setItems(FXCollections
				.observableArrayList(offlineContactModels));
		offlineList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		offlineList
				.setCellFactory(new Callback<ListView<ContactModel>, ListCell<ContactModel>>() {
					@Override
					public ListCell<ContactModel> call(
							ListView<ContactModel> param) {
						return new ContactListCell();
					}
				});

		// UNKNOWN
		List<ContactModel> unknownContactModels = new ArrayList<ContactModel>();
		for (ContactModel contactModel : contactModels) {
			if (contactModel.getStatus().equals(Status.UNKNOWN)) {
				unknownContactModels.add(contactModel);
			}
		}
		unknownList.setItems(FXCollections
				.observableArrayList(unknownContactModels));
		unknownList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		unknownList
				.setCellFactory(new Callback<ListView<ContactModel>, ListCell<ContactModel>>() {
					@Override
					public ListCell<ContactModel> call(
							ListView<ContactModel> param) {
						return new ContactListCell();
					}
				});
	}

	@FXML
	private void startChat(ActionEvent event) {
		ContactModel selected = getSelected();
		if (selected != null) {
			mainApp.startChatWith(selected, true);
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
			selected = unknownList.getSelectionModel().getSelectedItem();
		}
		return selected;
	}

	private void addHandlersForLists() {
		onlineList.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<ContactModel>() {
					@Override
					public void changed(
							ObservableValue<? extends ContactModel> observable,
							ContactModel oldValue, ContactModel newValue) {
						if (newValue != null) {
							dropOtherSelection(onlineList);
						}
					}
				});
		offlineList.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<ContactModel>() {
					@Override
					public void changed(
							ObservableValue<? extends ContactModel> observable,
							ContactModel oldValue, ContactModel newValue) {
						if (newValue != null) {
							dropOtherSelection(offlineList);
						}
					}
				});
		unknownList.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<ContactModel>() {
					@Override
					public void changed(
							ObservableValue<? extends ContactModel> observable,
							ContactModel oldValue, ContactModel newValue) {
						if (newValue != null) {
							dropOtherSelection(unknownList);
						}
					}
				});
	}

	private void dropOtherSelection(ListView<ContactModel> listView) {
		if (!offlineList.getId().equals(listView.getId())) {
			offlineList.getSelectionModel().clearSelection();
			LOGGER.info("Cleared offline");
		}
		if (!onlineList.getId().equals(listView.getId())) {
			onlineList.getSelectionModel().clearSelection();
			LOGGER.info("Cleared online");
		}
		if (!unknownList.getId().equals(listView.getId())) {
			unknownList.getSelectionModel().clearSelection();
		}
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}
