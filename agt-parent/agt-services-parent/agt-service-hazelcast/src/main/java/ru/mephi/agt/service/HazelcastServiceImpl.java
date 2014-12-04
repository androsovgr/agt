package ru.mephi.agt.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.model.Message;
import ru.mephi.agt.request.IdListRequest;
import ru.mephi.agt.request.IdRequest;
import ru.mephi.agt.request.MessageRequest;
import ru.mephi.agt.request.gui.GuiRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.IdListResponse;
import ru.mephi.agt.response.MessageListResponse;
import ru.mephi.agt.service.model.MessageList;
import ru.mephi.agt.service.util.Constants;
import ru.mephi.agt.util.ErrorCode;
import ru.mephi.agt.util.LogUtil;

import com.hazelcast.core.IMap;
import com.hazelcast.core.ISet;

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

	@Inject
	@Named(Constants.USER_SET)
	private ISet<Long> userSet;

	public HazelcastServiceImpl() {
	}

	@Override
	public BaseResponse checkLogined(GuiRequest request) {
		String methodName = "checkLogined";
		BaseResponse response = null;

		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			String uid = uidMap.get(request.getOwnId());
			if (uid != null && uid.equals(request.getUid())) {
				response = new BaseResponse();
			} else {
				response = new BaseResponse(ErrorCode.UNAUTHORIZED,
						"Can't find user with id=" + request.getOwnId()
								+ " and same uid");
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new BaseResponse(ErrorCode.INTERNAL_ERROR,
					"Can't get from uid map");
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
			uidMap.put(request.getOwnId(), request.getUid());
			userSet.add(request.getOwnId());
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
	public BaseResponse addMessage(MessageRequest request) {
		String methodName = "addMessage";
		BaseResponse response = null;

		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			long receiverId = request.getMessage().getMessageReceiver();
			MessageList messageList = messagesMap.get(receiverId);
			if (messageList == null) {
				messageList = new MessageList();
			}
			messageList.getMessages().add(request.getMessage());
			messagesMap.put(receiverId, messageList);
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new BaseResponse(ErrorCode.INTERNAL_ERROR,
					"Can't put into message map");
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);

		return response;
	}

	@Override
	public MessageListResponse receiveMessages(IdRequest request) {
		String methodName = "receiveMessages";
		MessageListResponse response = null;

		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			MessageList messageList = messagesMap.remove(request.getId());
			if (messageList != null) {
				response = new MessageListResponse(messageList.getMessages());
			} else {
				response = new MessageListResponse(new ArrayList<Message>());
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new MessageListResponse(ErrorCode.INTERNAL_ERROR,
					"Can't receive messages");
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);

		return response;
	}

	@Override
	public IdListResponse checkOnline(IdListRequest request) {
		String methodName = "checkLogined";
		IdListResponse response = null;

		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			List<Long> idList = new ArrayList<Long>();
			for (Long id : request.getIdList()) {
				if (userSet.contains(id)) {
					idList.add(id);
				}
			}
			response = new IdListResponse(idList);
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new IdListResponse(ErrorCode.INTERNAL_ERROR,
					"Check logined");
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);

		return response;
	}

	@Override
	public BaseResponse removeLogined(IdRequest request) {
		String methodName = "removeLogined";
		BaseResponse response = null;

		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			userSet.remove(request.getId());
			response = new BaseResponse();
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new IdListResponse(ErrorCode.INTERNAL_ERROR,
					"Check logined");
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);

		return response;
	}
}
