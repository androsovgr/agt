package ru.mephi.agt.response;

import java.util.List;

import ru.mephi.agt.model.Contact;
import ru.mephi.agt.util.ErrorCode;

public class ContactListResponse extends BaseResponse {

	protected List<Contact> contacts;

	@Override
	public String toString() {
		return "ContactListResponse [contacts=" + contacts + ", errorCode="
				+ errorCode + ", errorMessage=" + errorMessage + "]";
	}

	public ContactListResponse(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public ContactListResponse(List<Contact> contacts) {
		super();
		this.contacts = contacts;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public ContactListResponse() {
		super();
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

}
