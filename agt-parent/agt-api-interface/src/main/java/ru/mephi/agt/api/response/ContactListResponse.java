package ru.mephi.agt.api.response;

import java.util.List;

import ru.mephi.agt.model.Contact;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.util.ErrorCode;

public class ContactListResponse extends BaseResponse {

	private List<Contact> onlineContacts;
	private List<Contact> offlineContacts;

	public List<Contact> getOnlineContacts() {
		return onlineContacts;
	}

	public void setOnlineContacts(List<Contact> onlineContacts) {
		this.onlineContacts = onlineContacts;
	}

	public List<Contact> getOfflineContacts() {
		return offlineContacts;
	}

	public void setOfflineContacts(List<Contact> oflineContacts) {
		this.offlineContacts = oflineContacts;
	}

	public ContactListResponse(ErrorCode errorCode, String errorMessage,
			List<Contact> onlineContacts, List<Contact> oflineContacts) {
		super(errorCode, errorMessage);
		this.onlineContacts = onlineContacts;
		this.offlineContacts = oflineContacts;
	}

	public ContactListResponse(List<Contact> onlineContacts,
			List<Contact> oflineContacts) {
		super();
		this.onlineContacts = onlineContacts;
		this.offlineContacts = oflineContacts;
	}

	public ContactListResponse() {
		super();
	}

	public ContactListResponse(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	@Override
	public String toString() {
		return "ContactListResponse [onlineContacts=" + onlineContacts
				+ ", oflineContacts=" + offlineContacts + ", errorCode="
				+ errorCode + ", errorMessage=" + errorMessage + "]";
	}

}
