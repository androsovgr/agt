package ru.mephi.agt.api.request;

import ru.mephi.agt.request.gui.GuiRequest;

public class ContactGuiRequest extends GuiRequest {

	private long contactId;
	private long userId;
	private String displayName;

	@Override
	public String toString() {
		return "ContactGuiRequest [contactId=" + contactId + ", userId="
				+ userId + ", displayName=" + displayName + ", ownId=" + ownId
				+ ", uid=" + uid + ", transactionId=" + transactionId + "]";
	}

	public ContactGuiRequest(long ownId, String uid, long contactId,
			long userId, String displayName) {
		super(ownId, uid);
		this.contactId = contactId;
		this.userId = userId;
		this.displayName = displayName;
	}

	public ContactGuiRequest() {
		super();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

}
