package ru.mephi.agt.desktop.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.api.ApiInterface;
import ru.mephi.agt.api.response.ContactListResponse;
import ru.mephi.agt.api.response.MessageListResponse;
import ru.mephi.agt.desktop.model.ContactModel;
import ru.mephi.agt.desktop.model.UserModel;
import ru.mephi.agt.model.Contact;
import ru.mephi.agt.model.Message;
import ru.mephi.agt.model.User;
import ru.mephi.agt.request.LoginRequest;
import ru.mephi.agt.request.StringRequest;
import ru.mephi.agt.request.gui.GuiRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.IdResponse;
import ru.mephi.agt.response.LoginResponse;
import ru.mephi.agt.response.UserListResponse;
import ru.mephi.agt.util.LogUtil;

public class ServerInteractor {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServerInteractor.class);

	private ServerInteractor() {
		super();
	}

	public static LoginResponse login(String login, String password) {
		final String methodName = "register";
		LoginRequest request = null;
		LoginResponse response = null;
		try {
			long id = Long.parseLong(login);
			request = new LoginRequest(id, password);
			LogUtil.logStarted(LOGGER, methodName, request);
			ApiInterface api = ServerConnector.getApiInterface();
			if (api != null) {
				response = api.login(request);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	public static IdResponse register(String password) {
		final String methodName = "register";
		StringRequest request = new StringRequest(password);
		IdResponse response = null;
		try {
			LogUtil.logStarted(LOGGER, methodName, request);
			ApiInterface api = ServerConnector.getApiInterface();
			if (api != null) {
				response = api.register(request);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	public static ContactListResponse getContacts() {
		ContactListResponse response = null;

		List<Contact> contactsOnline = new ArrayList<Contact>();
		contactsOnline.add(new Contact(0, "����", null));
		contactsOnline.add(new Contact(1, "����", null));
		List<Contact> contactsOffline = new ArrayList<Contact>();
		contactsOffline.add(new Contact(2, "����", null));

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
		LOGGER.debug("Total got messages: {}", messages.size());

		return response;
	}

	public static UserListResponse searchUsers(GuiRequest guiRequest) {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setUserId(1);
		user.setNickName("�������");
		users.add(user);
		user = new User();
		user.setUserId(2);
		user.setNickName(UUID.randomUUID().toString());
		users.add(user);
		UserListResponse response = new UserListResponse(users);
		LOGGER.info("Total got users: {}", users.size());

		return response;
	}

	public static BaseResponse addUser(String displayName, UserModel userModel) {
		return new BaseResponse();
	}
}
