package ru.mephi.agt.request;

import ru.mephi.agt.model.Contact;

public class ContactRequest extends BaseRequest {

	protected Contact contact;

	@Override
	public String toString() {
		return "ContactRequest [contact=" + contact + ", transactionId="
				+ transactionId + "]";
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public ContactRequest() {
		super();
	}

	public ContactRequest(Contact contact) {
		super();
		this.contact = contact;
	}

	public ContactRequest(String transactionId, Contact contact) {
		super(transactionId);
		this.contact = contact;
	}

}
