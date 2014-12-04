package ru.mephi.agt.service;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.model.Message;
import ru.mephi.agt.request.IdRequest;
import ru.mephi.agt.request.MessageListRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.MessageListResponse;
import ru.mephi.agt.util.ErrorCode;
import ru.mephi.agt.util.LogUtil;

@Stateless
@Local(MessageService.class)
public class MessageServiceImpl implements MessageService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MessageService.class);

	@Inject
	private EntityManager em;

	@Override
	public BaseResponse storeMessages(MessageListRequest request) {
		final String methodName = "getMessages";
		LogUtil.logStarted(LOGGER, methodName, request);
		BaseResponse response = null;
		try {
			for (Message message : request.getMessages()) {
				em.persist(message);
			}
			em.flush();
			response = new BaseResponse();
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new BaseResponse(ErrorCode.INTERNAL_ERROR,
					"Can't store messages to DB");
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	@Override
	public MessageListResponse getMessages(IdRequest request) {
		final String methodName = "getMessages";
		LogUtil.logStarted(LOGGER, methodName, request);
		MessageListResponse response = null;
		try {
			Session session = (Session) em.getDelegate();
			Criteria cb = session.createCriteria(Message.class);
			cb.add(Restrictions.eq("messageReceiver", request.getId()));
			@SuppressWarnings("unchecked")
			List<Message> messages = cb.list();
			response = new MessageListResponse(messages);
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new MessageListResponse(ErrorCode.INTERNAL_ERROR,
					"Can't get messages from DB");
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

}
