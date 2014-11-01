package ru.mephi.agt.desktop.model;

import java.time.LocalDate;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.mephi.agt.model.Gender;

public class UserModel {

	private LongProperty id;
	private StringProperty nick;
	private StringProperty firstName;
	private StringProperty lastName;
	private ObjectProperty<LocalDate> birthDate;
	private StringProperty city;
	private StringProperty country;
	private ObjectProperty<Gender> gender;

	public UserModel() {
		this(0, null, null, null, null, null, null, null);
	}

	public UserModel(long id, String nick, String firstName, String lastName,
			LocalDate birthDate, String city, String country, Gender gender) {
		super();
		this.id = new SimpleLongProperty(id);
		this.nick = new SimpleStringProperty(nick);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.birthDate = new SimpleObjectProperty<LocalDate>(birthDate);
		this.city = new SimpleStringProperty(city);
		this.country = new SimpleStringProperty(country);
		this.gender = new SimpleObjectProperty<Gender>(gender);
	}

	public final LongProperty idProperty() {
		return this.id;
	}

	public final long getId() {
		return this.idProperty().get();
	}

	public final void setId(final long id) {
		this.idProperty().set(id);
	}

	public final StringProperty nickProperty() {
		return this.nick;
	}

	public final java.lang.String getNick() {
		return this.nickProperty().get();
	}

	public final void setNick(final java.lang.String nick) {
		this.nickProperty().set(nick);
	}

	public final StringProperty firstNameProperty() {
		return this.firstName;
	}

	public final java.lang.String getFirstName() {
		return this.firstNameProperty().get();
	}

	public final void setFirstName(final java.lang.String firstName) {
		this.firstNameProperty().set(firstName);
	}

	public final StringProperty lastNameProperty() {
		return this.lastName;
	}

	public final java.lang.String getLastName() {
		return this.lastNameProperty().get();
	}

	public final void setLastName(final java.lang.String lastName) {
		this.lastNameProperty().set(lastName);
	}

	public final ObjectProperty<LocalDate> birthDateProperty() {
		return this.birthDate;
	}

	public final java.time.LocalDate getBirthDate() {
		return this.birthDateProperty().get();
	}

	public final void setBirthDate(final java.time.LocalDate birthDate) {
		this.birthDateProperty().set(birthDate);
	}

	public final StringProperty cityProperty() {
		return this.city;
	}

	public final java.lang.String getCity() {
		return this.cityProperty().get();
	}

	public final void setCity(final java.lang.String city) {
		this.cityProperty().set(city);
	}

	public final StringProperty countryProperty() {
		return this.country;
	}

	public final java.lang.String getCountry() {
		return this.countryProperty().get();
	}

	public final void setCountry(final java.lang.String country) {
		this.countryProperty().set(country);
	}

	public final ObjectProperty<Gender> genderProperty() {
		return this.gender;
	}

	public final ru.mephi.agt.model.Gender getGender() {
		return this.genderProperty().get();
	}

	public final void setGender(final ru.mephi.agt.model.Gender gender) {
		this.genderProperty().set(gender);
	}

}
