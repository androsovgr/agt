package ru.mephi.agt.desktop.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.desktop.MainApp;
import ru.mephi.agt.desktop.component.ContactListCell;
import ru.mephi.agt.desktop.model.ContactModel;
import ru.mephi.agt.desktop.model.extractor.ContactExtractor;
import ru.mephi.agt.model.Status;

public class ContactsController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ContactsController.class);

	@FXML
	private ListView<ContactModel> onlineListView;

	@FXML
	private ListView<ContactModel> offlineListView;

	@FXML
	private ListView<ContactModel> unknownListView;

	private MainApp mainApp;

	private ObservableList<ContactModel> onlineList;
	private ObservableList<ContactModel> unknownList;
	private ObservableList<ContactModel> offlineList;

	@FXML
	private void initialize() {
		addHandlersForLists();
		addCellFactoriesForLists();
		onlineList = FXCollections.observableArrayList(new ContactExtractor());
		onlineListView.setItems(onlineList);
		onlineListView.getSelectionModel().setSelectionMode(
				SelectionMode.SINGLE);
		offlineList = FXCollections.observableArrayList(new ContactExtractor());
		offlineListView.setItems(offlineList);
		offlineListView.getSelectionModel().setSelectionMode(
				SelectionMode.SINGLE);
		unknownList = FXCollections.observableArrayList(new ContactExtractor());
		unknownListView.setItems(unknownList);
		unknownListView.getSelectionModel().setSelectionMode(
				SelectionMode.SINGLE);

	}

	public void updateContactList(List<ContactModel> delta) {
		selectionHandler(delta);
		checkStatusChangeWithReplace();
	}

	private void checkStatusChangeWithReplace() {
		checkStatusChangeWithReplaceForList(onlineList, Status.ONLINE);
		checkStatusChangeWithReplaceForList(offlineList, Status.OFFLINE);
		checkStatusChangeWithReplaceForList(unknownList, Status.UNKNOWN);
	}

	private void checkStatusChangeWithReplaceForList(
			ObservableList<ContactModel> list, Status status) {
		for (Iterator<ContactModel> iterator = list.iterator(); iterator
				.hasNext();) {
			ContactModel contactModel = iterator.next();
			if (contactModel.getStatus() != status) {
				iterator.remove();
				if (contactModel.getStatus() == Status.ONLINE) {
					onlineList.add(contactModel);
				} else if (contactModel.getStatus() == Status.OFFLINE) {
					offlineList.add(contactModel);
				} else if (contactModel.getStatus() == Status.UNKNOWN) {
					unknownList.add(contactModel);
				} else {
					LOGGER.warn("Unknown type of model: {}", contactModel);
				}
			}

		}
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
		ContactModel selected = onlineListView.getSelectionModel()
				.getSelectedItem();
		if (selected == null) {
			selected = offlineListView.getSelectionModel().getSelectedItem();
		}
		if (selected == null) {
			selected = unknownListView.getSelectionModel().getSelectedItem();
		}
		return selected;
	}

	private void addHandlersForLists() {
		onlineListView
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValue) -> dropOtherSelection(
								newValue, onlineListView));
		offlineListView
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValue) -> dropOtherSelection(
								newValue, offlineListView));
		unknownListView
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValue) -> dropOtherSelection(
								newValue, unknownListView));
	}

	private void addCellFactoriesForLists() {
		onlineListView.setCellFactory(column -> new ContactListCell());
		offlineListView.setCellFactory(column -> new ContactListCell());
		unknownListView.setCellFactory(column -> new ContactListCell());
	}

	private void dropOtherSelection(ContactModel newValue,
			ListView<ContactModel> listView) {
		if (newValue != null) {
			if (!offlineListView.getId().equals(listView.getId())) {
				offlineListView.getSelectionModel().clearSelection();
				LOGGER.info("Cleared offline");
			}
			if (!onlineListView.getId().equals(listView.getId())) {
				onlineListView.getSelectionModel().clearSelection();
				LOGGER.info("Cleared online");
			}
			if (!unknownListView.getId().equals(listView.getId())) {
				unknownListView.getSelectionModel().clearSelection();
			}
		}
	}

	private void selectionHandler(List<ContactModel> delta) {
		List<ContactModel> onlineContactModels = new ArrayList<ContactModel>();
		for (ContactModel contactModel : delta) {
			if (contactModel.getStatus().equals(Status.ONLINE)) {
				onlineContactModels.add(contactModel);
			}
		}
		onlineList.addAll(onlineContactModels);

		// OFFLINE

		List<ContactModel> offlineContactModels = new ArrayList<ContactModel>();
		for (ContactModel contactModel : delta) {
			if (contactModel.getStatus().equals(Status.OFFLINE)) {
				offlineContactModels.add(contactModel);
			}
		}
		offlineList.addAll(offlineContactModels);
		// UNKNOWN
		List<ContactModel> unknownContactModels = new ArrayList<ContactModel>();
		for (ContactModel contactModel : delta) {
			if (contactModel.getStatus().equals(Status.UNKNOWN)) {
				unknownContactModels.add(contactModel);
			}
		}
		unknownList.addAll(unknownContactModels);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}
