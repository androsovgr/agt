package ru.mephi.agt.desktop.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt._interface.request.BaseResponse;
import ru.mephi.agt._interface.request.GuiRequest;
import ru.mephi.agt._interface.response.ContactListResponse;
import ru.mephi.agt._interface.response.LoginResponse;
import ru.mephi.agt._interface.response.MessageListResponse;
import ru.mephi.agt.desktop.model.ContactModel;
import ru.mephi.agt.model.Contact;
import ru.mephi.agt.model.Message;

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
		contactsOffline.add(new Contact(2, "Маша", null));

		response = new ContactListResponse(contactsOnline, contactsOffline);

		return response;
	}

	public static BaseResponse sendMessage(ContactModel contact, String message) {
		LOGGER.info("Try send to contact: {} message:{}", contact, message);
		return new BaseResponse();
	}

	public static MessageListResponse getMessages(GuiRequest guiRequest) {
		List<Message> messages = new ArrayList<Message>();
		for (int i = 0; i < Math.round(Math.random() * 3); i++) {
			Message message = new Message();
			message.setMessageId(System.currentTimeMillis());
			message.setMessage(UUID.randomUUID().toString());
			message.setMessageSender(Math.round(Math.random() * 15));
			message.setMessageTime(new Date());
			messages.add(message);
		}
		MessageListResponse response = new MessageListResponse(messages);
		LOGGER.info("Total got messages: {}", messages.size());

		return response;
	}
}
