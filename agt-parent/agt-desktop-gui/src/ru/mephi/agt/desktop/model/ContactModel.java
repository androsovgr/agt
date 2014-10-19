package ru.mephi.agt.desktop.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.mephi.agt.model.Status;

public class ContactModel {

	private StringProperty displayNameProperty;
	private ObjectProperty<Status> statusProperty;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((displayNameProperty == null) ? 0 : displayNameProperty
						.hashCode());
		result = prime * result
				+ ((statusProperty == null) ? 0 : statusProperty.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactModel other = (ContactModel) obj;
		if (displayNameProperty == null) {
			if (other.displayNameProperty != null)
				return false;
		} else if (!displayNameProperty.equals(other.displayNameProperty))
			return false;
		if (statusProperty == null) {
			if (other.statusProperty != null)
				return false;
		} else if (!statusProperty.equals(other.statusProperty))
			return false;
		return true;
	}

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

	@Override
	public String toString() {
		return "ContactModel [displayNameProperty=" + displayNameProperty
				+ ", statusProperty=" + statusProperty + "]";
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
