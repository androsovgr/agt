package ru.mephi.agt.service;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.model.User;
import ru.mephi.agt.request.IdRequest;
import ru.mephi.agt.request.UserRequest;
import ru.mephi.agt.response.IdResponse;
import ru.mephi.agt.response.UserResponse;
import ru.mephi.agt.util.ErrorCode;
import ru.mephi.agt.util.LogUtil;

@Stateless
@Local(UserService.class)
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Inject
	private EntityManager em;

	@Override
	public UserResponse getUserById(IdRequest request) {
		final String methodName = "getUserById";
		LogUtil.logStarted(LOGGER, methodName, request);
		UserResponse response = new UserResponse();
		try {
			User user = em.find(User.class, request.getId());
			response.setUser(user);
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response.setErrorCode(ErrorCode.INTERNAL_ERROR);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	@Override
	public IdResponse addUser(UserRequest request) {
		final String methodName = "addUser";
		LogUtil.logStarted(LOGGER, methodName, request);
		IdResponse response = new IdResponse();
		try {
			User user = request.getUser();
			em.persist(user);
			em.flush();
			response.setId(user.getUserId());
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response.setErrorCode(ErrorCode.INTERNAL_ERROR);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}
}
