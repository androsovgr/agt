package ru.mephi.agt.desktop.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt._interface.response.ContactListResponse;
import ru.mephi.agt._interface.response.LoginResponse;
import ru.mephi.agt.model.Contact;

public class ServerInteractor {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServerInteractor.class);

	private ServerInteractor() {
		super();
	}

	public static LoginResponse login(String login, String password) {
		LOGGER.info("Try login with login: {} and password: {} ", login,
				password);
		// TODO: implement
		return null;
	}

	public static void register(String password) {
		LOGGER.info("Try register with password: {} ", password);
		// TODO: implement
	}

	public static ContactListResponse getContacts() {
		ContactListResponse response = null;

		List<Contact> contactsOnline = new ArrayList<Contact>();
		contactsOnline.add(new Contact(0, "Петя", null));
		contactsOnline.add(new Contact(1, "Вася", null));
		List<Contact> contactsOffline = new ArrayList<Contact>();
		contactsOffline.add(new Contact(0, "Маша", null));

		response = new ContactListResponse(contactsOnline, contactsOffline);

		return response;
	}
}
