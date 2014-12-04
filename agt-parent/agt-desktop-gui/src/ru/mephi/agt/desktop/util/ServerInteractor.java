package ru.mephi.agt.desktop.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.api.ApiService;
import ru.mephi.agt.api.request.AddContactGuiRequest;
import ru.mephi.agt.api.request.IdListGuiRequest;
import ru.mephi.agt.api.request.SendMessageGuiRequest;
import ru.mephi.agt.api.request.UserGuiRequest;
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
import ru.mephi.agt.response.ContactListResponse;
import ru.mephi.agt.response.IdListResponse;
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

	public static LoginResponse login(long login, String password) {
		final String methodName = "register";
		LoginRequest request = null;
		LoginResponse response = null;
		try {
			request = new LoginRequest(login, password);
			LogUtil.logStarted(LOGGER, methodName, request);
			ApiService api = ServerConnector.getApiInterface();
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
			ApiService api = ServerConnector.getApiInterface();
			if (api != null) {
				response = api.register(request);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	public static ContactListResponse getContacts(long ownId, String uid) {
		final String methodName = "register";
		ContactListResponse response = null;
		GuiRequest request = null;
		try {
			request = new GuiRequest(ownId, uid);
			LogUtil.logStarted(LOGGER, methodName, request);
			ApiService api = ServerConnector.getApiInterface();
			if (api != null) {
				response = api.getContactsFor(request);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	public static BaseResponse sendMessage(ContactModel contact,
			String message, long ownId, String uid) {
		final String methodName = "sendMessage";
		BaseResponse response = null;
		SendMessageGuiRequest request = null;
		try {
			request = new SendMessageGuiRequest(ownId, uid, contact.getId(),
					message);
			LogUtil.logStarted(LOGGER, methodName, request);
			ApiService api = ServerConnector.getApiInterface();
			if (api != null) {
				response = api.sendMessage(request);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	public static MessageListResponse getMessages(GuiRequest guiRequest) {
		List<Message> messages = new ArrayList<Message>();
		for (int i = 0; i < /* Math.round(Math.random() * 3) */0; i++) {
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

	public static UserListResponse searchUsers(User filters, String uid,
			long ownId) {
		final String methodName = "register";
		UserListResponse response = null;
		UserGuiRequest request = null;
		try {
			request = new UserGuiRequest(ownId, uid, filters);
			LogUtil.logStarted(LOGGER, methodName, request);
			ApiService api = ServerConnector.getApiInterface();
			if (api != null) {
				response = api.search(request);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	public static BaseResponse addUser(String displayName, UserModel userModel,
			String uid, long ownId) {
		final String methodName = "register";
		BaseResponse response = null;
		AddContactGuiRequest request = null;
		try {
			request = new AddContactGuiRequest(ownId, uid, userModel.getId(),
					displayName);
			LogUtil.logStarted(LOGGER, methodName, request);
			ApiService api = ServerConnector.getApiInterface();
			if (api != null) {
				response = api.addContact(request);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	public static IdListResponse getOnlineOf(List<Contact> users, String uid,
			long ownId) {
		final String methodName = "getOnlineOf";
		IdListResponse response = null;
		IdListGuiRequest request = null;
		try {
			List<Long> userIds = new ArrayList<Long>();
			for (Contact contact : users) {
				userIds.add(contact.getUser().getUserId());
			}
			request = new IdListGuiRequest(ownId, uid, userIds);
			LogUtil.logStarted(LOGGER, methodName, request);
			ApiService api = ServerConnector.getApiInterface();
			if (api != null) {
				response = api.getStatuses(request);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}
}
