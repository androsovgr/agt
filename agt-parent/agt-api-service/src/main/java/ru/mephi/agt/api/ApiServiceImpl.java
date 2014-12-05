package ru.mephi.agt.api;

import java.util.Date;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.api.request.AddContactGuiRequest;
import ru.mephi.agt.api.request.IdGuiRequest;
import ru.mephi.agt.api.request.IdListGuiRequest;
import ru.mephi.agt.api.request.SendMessageGuiRequest;
import ru.mephi.agt.api.request.UserGuiRequest;
import ru.mephi.agt.model.Contact;
import ru.mephi.agt.model.Message;
import ru.mephi.agt.model.User;
import ru.mephi.agt.request.ContactRequest;
import ru.mephi.agt.request.IdListRequest;
import ru.mephi.agt.request.IdRequest;
import ru.mephi.agt.request.LoginRequest;
import ru.mephi.agt.request.MessageRequest;
import ru.mephi.agt.request.StringRequest;
import ru.mephi.agt.request.UserRequest;
import ru.mephi.agt.request.gui.GuiRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.ContactListResponse;
import ru.mephi.agt.response.IdListResponse;
import ru.mephi.agt.response.IdResponse;
import ru.mephi.agt.response.LoginResponse;
import ru.mephi.agt.response.MessageListResponse;
import ru.mephi.agt.response.UserListResponse;
import ru.mephi.agt.response.UserResponse;
import ru.mephi.agt.service.ContactService;
import ru.mephi.agt.service.HazelcastService;
import ru.mephi.agt.service.LoginOrchetrationService;
import ru.mephi.agt.service.MessageOrchetrationService;
import ru.mephi.agt.service.MessageService;
import ru.mephi.agt.service.UserService;
import ru.mephi.agt.util.ErrorCode;
import ru.mephi.agt.util.LogUtil;

public class ApiServiceImpl implements ApiService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ApiServiceImpl.class);

	@EJB
	private LoginOrchetrationService loginService;

	@EJB
	private MessageOrchetrationService messageOrchetrationService;

	@EJB
	private HazelcastService hazelcastService;

	@EJB
	private UserService userService;

	@EJB
	private ContactService contactService;

	@EJB
	private MessageService messageService;

	public ApiServiceImpl() {
	}

	@Override
	public LoginResponse login(LoginRequest request) {
		return loginService.tryLogin(request);
	}

	@Override
	public IdResponse register(StringRequest request) {
		String methodName = "register";
		IdResponse response = null;
		LogUtil.logStarted(LOGGER, methodName, request);
		response = loginService.register(request);
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	@Override
	public BaseResponse test(StringRequest request) {
		return hazelcastService.setLogined(new GuiRequest(1, request
				.getTransactionId()));
	}

	@Override
	public BaseResponse test2(StringRequest request) throws NamingException {
		new InitialContext().lookup(request.getString());
		System.out.println();
		return new BaseResponse();
	}

	@Override
	public UserListResponse search(UserGuiRequest request) {
		String methodName = "search";
		UserListResponse response = null;
		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			if (checkLogined(request)) {
				UserRequest searchRequest = new UserRequest(
						request.getTransactionId(), request.getUser());
				response = userService.searchUsers(searchRequest);
			} else {
				response = new UserListResponse(ErrorCode.UNAUTHORIZED, null);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new UserListResponse(ErrorCode.INTERNAL_ERROR, null);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	@Override
	public BaseResponse addContact(AddContactGuiRequest request) {
		String methodName = "search";
		BaseResponse response = null;
		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			if (checkLogined(request)) {
				User user = new User();
				user.setUserId(request.getUserId());
				Contact contact = new Contact(0, request.getOwnId(),
						request.getDisplayName(), user);
				ContactRequest contactRequest = new ContactRequest(
						request.getTransactionId(), contact);
				response = contactService.addContactFor(contactRequest);
			} else {
				response = new BaseResponse(ErrorCode.UNAUTHORIZED, null);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new BaseResponse(ErrorCode.INTERNAL_ERROR, null);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	@Override
	public ContactListResponse getContactsFor(GuiRequest request) {
		String methodName = "search";
		ContactListResponse response = null;
		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			if (checkLogined(request)) {
				IdRequest idRequest = new IdRequest(request.getTransactionId(),
						request.getOwnId());
				response = contactService.getContactsFor(idRequest);
			} else {
				response = new ContactListResponse(ErrorCode.UNAUTHORIZED, null);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new ContactListResponse(ErrorCode.INTERNAL_ERROR, null);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	private boolean checkLogined(GuiRequest request) {
		BaseResponse response = hazelcastService.checkLogined(request);
		return ErrorCode.OK == response.getErrorCode();
	}

	@Override
	public IdListResponse getStatuses(IdListGuiRequest request) {
		String methodName = "getStatuses";
		IdListResponse response = null;
		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			if (checkLogined(request)) {
				IdListRequest idListRequest = new IdListRequest(
						request.getTransactionId(), request.getIdList());
				response = hazelcastService.checkOnline(idListRequest);
			} else {
				response = new IdListResponse(ErrorCode.UNAUTHORIZED, null);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new IdListResponse(ErrorCode.INTERNAL_ERROR, null);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	@Override
	public BaseResponse sendMessage(SendMessageGuiRequest request) {
		String methodName = "sendMessage";
		BaseResponse response = null;
		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			if (checkLogined(request)) {
				Message message = new Message(0L, new Date(),
						request.getMessage(), request.getOwnId(),
						request.getReceiverId());
				response = messageOrchetrationService
						.sendMessage(new MessageRequest(request
								.getTransactionId(), message));
			} else {
				response = new BaseResponse(ErrorCode.UNAUTHORIZED, null);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new BaseResponse(ErrorCode.INTERNAL_ERROR, null);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	@Override
	public MessageListResponse receiveMessages(GuiRequest request) {
		String methodName = "receiveMessages";
		MessageListResponse response = null;
		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			if (checkLogined(request)) {
				IdRequest receiveMessagesRequest = new IdRequest(
						request.getTransactionId(), request.getOwnId());
				response = hazelcastService
						.receiveMessages(receiveMessagesRequest);
			} else {
				response = new MessageListResponse(ErrorCode.UNAUTHORIZED, null);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new MessageListResponse(ErrorCode.INTERNAL_ERROR, null);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	@Override
	public MessageListResponse receiveStoredMessages(GuiRequest request) {
		String methodName = "receiveStoredMessages";
		MessageListResponse response = null;
		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			if (checkLogined(request)) {
				IdRequest receiveMessagesRequest = new IdRequest(
						request.getTransactionId(), request.getOwnId());
				response = messageService
						.getMessagesAndDelete(receiveMessagesRequest);
			} else {
				response = new MessageListResponse(ErrorCode.UNAUTHORIZED, null);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new MessageListResponse(ErrorCode.INTERNAL_ERROR, null);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	@Override
	public UserResponse getUserInfo(IdGuiRequest request) {
		String methodName = "getUserInfo";
		UserResponse response = null;
		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			if (checkLogined(request)) {
				IdRequest idRequest = new IdRequest(request.getTransactionId(),
						request.getId());
				response = userService.getUserById(idRequest);
			} else {
				response = new UserResponse(ErrorCode.UNAUTHORIZED, null);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new UserResponse(ErrorCode.INTERNAL_ERROR, null);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	@Override
	public BaseResponse updateSelfInfo(UserGuiRequest request) {
		String methodName = "updateSelfInfo";
		BaseResponse response = null;
		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			if (checkLogined(request)) {
				User user = request.getUser();
				user.setUserId(request.getOwnId());
				UserRequest userRequest = new UserRequest(user);
				response = userService.updateUser(userRequest);
			} else {
				response = new BaseResponse(ErrorCode.UNAUTHORIZED, null);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new BaseResponse(ErrorCode.INTERNAL_ERROR, null);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}
}
