package ru.mephi.agt.model;

public class Contact {

	private long contactId;
	private String displayName;
	private User user;

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", displayName="
				+ displayName + ", user=" + user + "]";
	}

	public long getContactId() {
		return contactId;
	}

	public Contact(long contactId, String displayName, User user) {
		super();
		this.contactId = contactId;
		this.displayName = displayName;
		this.user = user;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Contact() {
		super();
	}

}
