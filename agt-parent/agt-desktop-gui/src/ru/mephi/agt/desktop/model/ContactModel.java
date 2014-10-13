package ru.mephi.agt.desktop.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.mephi.agt.model.Status;

public class ContactModel {

	private StringProperty displayNameProperty;
	private ObjectProperty<Status> statusProperty;

	public ContactModel() {
		this(null, null);
	}

	public ContactModel(String displayName, Status status) {
		super();
		this.displayNameProperty = new SimpleStringProperty(displayName);
		this.statusProperty = new SimpleObjectProperty<Status>(status);
	}

	public Status getStatus() {
		return statusProperty.get();
	}

	public void setStatus(Status status) {
		this.statusProperty.set(status);
	}

	public String getDisplayName() {
		return displayNameProperty.get();
	}

	public void setDisplayName(String displayName) {
		this.displayNameProperty.set(displayName);
	}

	public StringProperty displayNameProperty() {
		return displayNameProperty;
	}
}
