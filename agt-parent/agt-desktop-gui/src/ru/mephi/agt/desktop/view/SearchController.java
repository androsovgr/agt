package ru.mephi.agt.desktop.view;

import java.awt.TextField;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.mephi.agt.api.request.GuiRequest;
import ru.mephi.agt.desktop.component.GenderTableCell;
import ru.mephi.agt.desktop.model.UserModel;
import ru.mephi.agt.desktop.model.converter.GenderConverter;
import ru.mephi.agt.desktop.util.ControllerUtil;
import ru.mephi.agt.desktop.util.ServerInteractor;
import ru.mephi.agt.model.Gender;
import ru.mephi.agt.response.UserListResponse;

public class SearchController {

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
	}

	@FXML
	private void search() {
		UserListResponse response = ServerInteractor
				.searchUsers(new GuiRequest());
		if (ControllerUtil.handleResponse(response)) {
			response.getUsers();
			// searchTable.setItems(FXCollections.observableArrayList(response
			// .getUsers()));
		} else {
			// TODO
		}
	}

	@FXML
	private void startChat() {

	}

	@FXML
	private void addToContactList() {

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
}
