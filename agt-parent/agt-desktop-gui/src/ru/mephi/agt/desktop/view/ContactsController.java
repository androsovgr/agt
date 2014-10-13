package ru.mephi.agt.desktop.view;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.mephi.agt._interface.response.ContactListResponse;
import ru.mephi.agt.desktop.MainApp;
import ru.mephi.agt.desktop.converter.ContactConverter;
import ru.mephi.agt.desktop.model.ContactModel;
import ru.mephi.agt.desktop.util.ServerInteractor;
import ru.mephi.agt.model.Contact;
import ru.mephi.agt.model.Status;

public class ContactsController {

	@FXML
	private TableView<ContactModel> onlineTable;
	@FXML
	private TableColumn<ContactModel, String> onlineNameColumn;

	@FXML
	private void initialize() {
		onlineNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.displayNameProperty());
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
			onlineTable.setItems(FXCollections
					.observableArrayList(onlineContactModels));

			// OFFLINE
			List<Contact> offlineContacts = response.getOfflineContacts();
			List<ContactModel> offlineContactModels = ContactConverter
					.toGuiModelList(offlineContacts);
			for (ContactModel contactModel : offlineContactModels) {
				contactModel.setStatus(Status.OFFLINE);
			}
		}
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}
