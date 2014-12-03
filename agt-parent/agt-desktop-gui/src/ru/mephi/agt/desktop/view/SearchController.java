package ru.mephi.agt.desktop.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.control.Notifications;
import org.controlsfx.dialog.Dialogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.desktop.MainApp;
import ru.mephi.agt.desktop.component.GenderTableCell;
import ru.mephi.agt.desktop.converter.UserConverter;
import ru.mephi.agt.desktop.model.UserModel;
import ru.mephi.agt.desktop.model.converter.GenderConverter;
import ru.mephi.agt.desktop.util.ControllerUtil;
import ru.mephi.agt.desktop.util.ServerInteractor;
import ru.mephi.agt.model.Gender;
import ru.mephi.agt.model.User;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.UserListResponse;

public class SearchController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SearchController.class);

	@FXML
	private TextField idField;
	@FXML
	private TextField nickField;
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private DatePicker birthDateField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField countryField;
	@FXML
	private ComboBox<Gender> genderField;

	@FXML
	private TableView<UserModel> searchTable;
	@FXML
	private TableColumn<UserModel, Number> idColumn;
	@FXML
	private TableColumn<UserModel, String> nickColumn;
	@FXML
	private TableColumn<UserModel, String> firstNameColumn;
	@FXML
	private TableColumn<UserModel, String> lastNameColumn;
	@FXML
	private TableColumn<UserModel, LocalDate> birthDateColumn;
	@FXML
	private TableColumn<UserModel, String> cityColumn;
	@FXML
	private TableColumn<UserModel, String> countryColumn;
	@FXML
	private TableColumn<UserModel, Gender> genderColumn;
	private MainApp mainApp;
	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	private void initialize() {
		genderField.setConverter(new GenderConverter());
		genderField.getItems().addAll(null, Gender.MALE, Gender.FEMALE,
				Gender.SHEMALE, Gender.EMPTY);
		initColumns();
	}

	@FXML
	private void clearFilters() {
		idField.setText("");
		nickField.setText("");
		firstNameField.setText("");
		lastNameField.setText("");
		birthDateField.setValue(null);
		cityField.setText("");
		countryField.setText("");
		genderField.setValue(null);
		searchTable.setItems(FXCollections.emptyObservableList());
	}

	@FXML
	private void search() {
		UserListResponse response = ServerInteractor.searchUsers(collectUser(),
				mainApp.getUid(), mainApp.getOwnId());
		if (ControllerUtil.handleResponse(response)) {
			List<User> users = response.getUsers();
			List<UserModel> userModels = UserConverter
					.convertToGuiModelList(users);
			searchTable.setItems(FXCollections.observableArrayList(userModels));
		}
	}

	private User collectUser() {
		User user = new User();

		try {
			user.setUserId(Long.parseLong((idField.getText())));
		} catch (Exception e) {
			LOGGER.warn("Invalid filters");
		}

		return user;
	}

	@FXML
	private void startChat() {
		UserModel selected = searchTable.getSelectionModel().getSelectedItem();
		if (selected != null) {
			mainApp.startChatWith(selected);
		}
	}

	@FXML
	private void addToContactList() {
		UserModel selected = searchTable.getSelectionModel().getSelectedItem();
		if (selected != null) {
			Optional<String> dialogResponse = Dialogs.create().owner(stage)
					.title("Добавление нового контакта")
					.message("Введите желаемое имя контакта:")
					.showTextInput(selected.getNick());
			if (dialogResponse.isPresent()) {
				String displayName = dialogResponse.get();
				BaseResponse response = ServerInteractor.addUser(displayName,
						selected, mainApp.getUid(), mainApp.getOwnId());
				if (ControllerUtil.handleResponse(response)) {
					Notifications
							.create()
							.title("Добавление пользователя")
							.text("Пользователь " + displayName
									+ " успешно добавлен в контакт-лист")
							.showInformation();
					mainApp.updateContactMapPutIntoContacts();
				}
			}

		}
	}

	private void initColumns() {
		idColumn.setCellValueFactory(cellData -> cellData.getValue()
				.idProperty());
		nickColumn.setCellValueFactory(cellData -> cellData.getValue()
				.nickProperty());
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.lastNameProperty());
		birthDateColumn.setCellValueFactory(cellData -> cellData.getValue()
				.birthDateProperty());
		cityColumn.setCellValueFactory(cellData -> cellData.getValue()
				.cityProperty());
		countryColumn.setCellValueFactory(cellData -> cellData.getValue()
				.countryProperty());
		genderColumn.setCellValueFactory(cellData -> cellData.getValue()
				.genderProperty());
		genderColumn.setCellFactory(column -> new GenderTableCell());
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
