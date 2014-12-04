package ru.mephi.agt.service;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.request.IdRequest;
import ru.mephi.agt.request.MessageRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.BooleanResponse;
import ru.mephi.agt.util.ErrorCode;
import ru.mephi.agt.util.LogUtil;

@Stateful
@Local(MessageOrchetrationService.class)
public class MessageOrchetrationServiceImpl implements
		MessageOrchetrationService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MessageOrchetrationServiceImpl.class);

	@EJB
	private UserService userServiceInterface;

	@EJB
	private HazelcastService hazelcastService;

	@EJB
	private MessageService messageService;

	@Override
	public BaseResponse sendMessage(MessageRequest request) {
		String methodName = "sendMessage";
		BaseResponse response = null;
		LogUtil.logStarted(LOGGER, methodName, request);

		try {
			IdRequest checkOnlineRequest = new IdRequest(
					request.getTransactionId(), request.getMessage()
							.getMessageReceiver());
			BooleanResponse checkOnlineResponse = hazelcastService
					.checkOnline(checkOnlineRequest);
			if (ErrorCode.OK == checkOnlineResponse.getErrorCode()) {
				if (checkOnlineResponse.isBool()) {
					response = hazelcastService.addMessage(request);
				} else {
					response = messageService.storeMessage(request);
				}
			} else {
				response = new BaseResponse(checkOnlineResponse.getErrorCode(),
						checkOnlineResponse.getErrorMessage());
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new BaseResponse(ErrorCode.INTERNAL_ERROR, null);
		}

		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

}
