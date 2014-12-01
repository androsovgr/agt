package ru.mephi.agt.service;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.request.gui.GuiRequest;
import ru.mephi.agt.request.gui.MessageGuiRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.MessageListResponse;
import ru.mephi.agt.service.model.MessageList;
import ru.mephi.agt.service.util.Constants;
import ru.mephi.agt.util.ErrorCode;
import ru.mephi.agt.util.LogUtil;

import com.hazelcast.core.IMap;

@Stateless
@Local(HazelcastService.class)
public class HazelcastServiceImpl implements HazelcastService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(HazelcastServiceImpl.class);

	@Inject
	@Named(Constants.UID_MAP)
	private IMap<Long, String> uidMap;

	@Inject
	@Named(Constants.MESSAGES_MAP)
	private IMap<Long, MessageList> messagesMap;

	public HazelcastServiceImpl() {
	}

	private BaseResponse checkLoginedPrivate(GuiRequest request) {
		String uid = uidMap.get(request.getId());
		BaseResponse response = null;
		if (uid != null && uid.equals(request.getUid())) {
			response = new BaseResponse();
		} else {
			response = new BaseResponse(ErrorCode.UNAUTHORIZED,
					"Can't find user with id=" + request.getId()
							+ " and same uid");
		}
		return response;
	}

	@Override
	public BaseResponse checkLogined(GuiRequest request) {
		String methodName = "checkLogined";
		BaseResponse response = null;

		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			response = checkLoginedPrivate(request);
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new BaseResponse(ErrorCode.INTERNAL_ERROR,
					"Can't put into uid map");
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);

		return response;
	}

	@Override
	public BaseResponse setLogined(GuiRequest request) {
		String methodName = "setLogined";
		BaseResponse response = null;

		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			uidMap.put(request.getId(), request.getUid());
			response = new BaseResponse();
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new BaseResponse(ErrorCode.INTERNAL_ERROR,
					"Can't put into uid map");
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);

		return response;
	}

	@Override
	public BaseResponse addMessage(MessageGuiRequest request) {
		String methodName = "addMessage";
		BaseResponse response = null;

		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			BaseResponse loginedResponse = checkLoginedPrivate(request);
			if (ErrorCode.OK == loginedResponse.getErrorCode()) {
				MessageList messageList = messagesMap.get(request.getId());
				if (messageList == null) {
					messageList = new MessageList();
				}
				messageList.getMessages().add(request.getMessage());
				messagesMap.put(request.getId(), messageList);
			} else {
				response = loginedResponse;
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new BaseResponse(ErrorCode.INTERNAL_ERROR,
					"Can't put into uid map");
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
			BaseResponse loginedResponse = checkLoginedPrivate(request);
			if (ErrorCode.OK == loginedResponse.getErrorCode()) {
				MessageList messageList = messagesMap.get(request.getId());
				if (messageList != null) {
					response = new MessageListResponse(
							messageList.getMessages());
				} else {
					response = new MessageListResponse();
				}
			} else {
				response = new MessageListResponse(
						loginedResponse.getErrorCode(),
						loginedResponse.getErrorMessage());
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new MessageListResponse(ErrorCode.INTERNAL_ERROR,
					"Can't put into uid map");
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);

		return response;
	}

}
