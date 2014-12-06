package ru.mephi.agt.desktop.converter;

import java.util.ArrayList;
import java.util.List;

import ru.mephi.agt.desktop.model.ContactModel;
import ru.mephi.agt.model.Contact;

public class ContactConverter {

	public static ContactModel toGuiModel(Contact contact) {
		ContactModel contactModel = null;

		if (contact != null) {
			contactModel = new ContactModel();
			contactModel.setDisplayName(contact.getDisplayName());
			contactModel.setUserId(contact.getUser().getUserId());
			contactModel.setContactId(contact.getContactId());
		}

		return contactModel;
	}

	public static List<ContactModel> toGuiModelList(List<Contact> contacts) {
		List<ContactModel> contactModels = null;

		if (contacts != null) {
			contactModels = new ArrayList<ContactModel>();
			for (Contact contact : contacts) {
				contactModels.add(toGuiModel(contact));
			}
		}

		return contactModels;
	}

}
