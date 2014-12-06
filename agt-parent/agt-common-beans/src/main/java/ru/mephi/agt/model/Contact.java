package ru.mephi.agt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {

	@Id
	@Column(name = "contact_id")
	private long contactId;
	@Column(name = "owner_user_id", updatable = false)
	private long ownerUserId;
	@Column(name = "display_name")
	private String displayName;
	@ManyToOne
	@JoinColumn(name = "contact_user_id", updatable = false)
	private User user;

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", ownerUserId="
				+ ownerUserId + ", displayName=" + displayName + ", user="
				+ user + "]";
	}

	public long getContactId() {
		return contactId;
	}

	public Contact(long contactId, long ownerUserId, String displayName,
			User user) {
		super();
		this.contactId = contactId;
		this.ownerUserId = ownerUserId;
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

	public long getOwnerUserId() {
		return ownerUserId;
	}

	public void setOwnerUserId(long ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

}
