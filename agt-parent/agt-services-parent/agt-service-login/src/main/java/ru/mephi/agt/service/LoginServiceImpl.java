package ru.mephi.agt.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.model.User;
import ru.mephi.agt.request.IdRequest;
import ru.mephi.agt.request.LoginRequest;
import ru.mephi.agt.request.MessageListRequest;
import ru.mephi.agt.request.StringRequest;
import ru.mephi.agt.request.UserRequest;
import ru.mephi.agt.request.gui.GuiRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.IdResponse;
import ru.mephi.agt.response.LoginResponse;
import ru.mephi.agt.response.MessageListResponse;
import ru.mephi.agt.response.UserResponse;
import ru.mephi.agt.util.ErrorCode;
import ru.mephi.agt.util.LogUtil;

@Stateful
@Local(LoginService.class)
public class LoginServiceImpl implements LoginService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoginServiceImpl.class);

	@EJB
	private UserService userServiceInterface;

	@EJB
	private HazelcastService hazelcastService;

	@EJB
	private MessageService messageService;

	@Override
	public LoginResponse tryLogin(LoginRequest request) {
		String methodName = "tryLogin";
		LogUtil.logStarted(LOGGER, methodName, request);
		LoginResponse response = new LoginResponse(ErrorCode.UNAUTHORIZED, null);

		try {
			IdRequest idRequest = new IdRequest(request.getTransactionId(),
					request.getId());
			UserResponse userResponse = userServiceInterface
					.getUserById(idRequest);
			if (userResponse.getErrorCode() == ErrorCode.OK) {
				if (userResponse.getUser() != null) {
					String passwordHashed = userResponse.getUser()
							.getPassword();
					String passwordHashed2 = getHash(request.getPassword());
					if (passwordHashed.equals(passwordHashed2)) {
						String uid = UUID.randomUUID().toString();
						GuiRequest guiRequest = new GuiRequest(
								request.getTransactionId(), request.getId(),
								uid);
						BaseResponse hzResponse = hazelcastService
								.setLogined(guiRequest);
						if (ErrorCode.OK == hzResponse.getErrorCode()) {
							response = new LoginResponse(uid);
						} else {
							response = new LoginResponse(
									hzResponse.getErrorCode(),
									hzResponse.getErrorMessage());
						}
					}
				}
			} else {
				response = new LoginResponse(userResponse.getErrorCode(),
						userResponse.getErrorMessage());
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
		}

		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	@Override
	public IdResponse register(StringRequest request) {
		String methodName = "register";
		IdResponse response = null;
		LogUtil.logStarted(LOGGER, methodName, request);

		try {
			String password = request.getString();
			String passwordHash = getHash(password);
			User user = new User();
			user.setPassword(passwordHash);
			UserRequest userRequest = new UserRequest(
					request.getTransactionId(), user);
			response = userServiceInterface.addUser(userRequest);
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
		}

		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	private String getHash(String string) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		// Add password bytes to digest
		md.update(string.getBytes());
		// Get the hash's bytes
		byte[] bytes = md.digest();
		// This bytes[] has bytes in decimal format;
		// Convert it to hexadecimal format
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		// Get complete hashed password in hex format
		return sb.toString();
	}

	@Override
	public BaseResponse logout(IdRequest request) {
		String methodName = "logout";
		BaseResponse response = null;
		LogUtil.logStarted(LOGGER, methodName, request);

		try {
			BaseResponse removeLoginedResponse = hazelcastService
					.removeLogined(request);
			if (ErrorCode.OK == removeLoginedResponse.getErrorCode()) {
				MessageListResponse receiveMessagesResponse = hazelcastService
						.receiveMessages(request);
				if (ErrorCode.OK == receiveMessagesResponse.getErrorCode()) {
					MessageListRequest storeMessagesRequest = new MessageListRequest(
							request.getTransactionId(),
							receiveMessagesResponse.getMessages());
					response = messageService
							.storeMessages(storeMessagesRequest);
				} else {
					response = new BaseResponse(
							receiveMessagesResponse.getErrorCode(),
							receiveMessagesResponse.getErrorMessage());
				}
			} else {
				response = removeLoginedResponse;
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new BaseResponse(ErrorCode.INTERNAL_ERROR, null);
		}

		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}
}
