package ru.mephi.agt.model;

import java.util.Date;

public class User {

	private long userId;
	private String nickName;
	private String firstName;
	private String lastName;
	private String password;
	private Date birthDate;
	private String city;
	private String country;
	private String phone;
	private Gender gender;
	private Role role;

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", nickName=" + nickName
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", birthDate=" + birthDate
				+ ", city=" + city + ", country=" + country + ", phone="
				+ phone + ", gender=" + gender + ", role=" + role + "]";
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
