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

import ru.mephi.agt.model.User;
import ru.mephi.agt.request.IdRequest;
import ru.mephi.agt.request.UserRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.IdResponse;
import ru.mephi.agt.response.UserListResponse;
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

	@Override
	public BaseResponse updateUser(UserRequest request) {
		final String methodName = "updateUser";
		LogUtil.logStarted(LOGGER, methodName, request);
		BaseResponse response = null;
		try {
			User user = request.getUser();
			User oldValue = em.find(User.class, user.getUserId());
			user.setPassword(oldValue.getPassword());
			em.merge(user);
			em.flush();
			response = new BaseResponse();
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new BaseResponse(ErrorCode.INTERNAL_ERROR, null);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}

	@Override
	public UserListResponse searchUsers(UserRequest request) {
		final String methodName = "searchUsers";
		LogUtil.logStarted(LOGGER, methodName, request);
		UserListResponse response = null;
		try {
			Session session = (Session) em.getDelegate();
			Criteria cb = session.createCriteria(User.class);
			if (request.getUser().getUserId() != 0) {
				cb.add(Restrictions.eqOrIsNull("userId", request.getUser()
						.getUserId()));
			}
			if (request.getUser().getNickName() != null
					&& !request.getUser().getNickName().isEmpty()) {
				cb.add(Restrictions.eqOrIsNull("nickName", request.getUser()
						.getNickName()));
			}
			// ---
			if (request.getUser().getFirstName() != null
					&& !request.getUser().getFirstName().isEmpty()) {
				cb.add(Restrictions.eqOrIsNull("firstName", request.getUser()
						.getFirstName()));
			}
			if (request.getUser().getLastName() != null
					&& !request.getUser().getLastName().isEmpty()) {
				cb.add(Restrictions.eqOrIsNull("lastName", request.getUser()
						.getLastName()));
			}
			if (request.getUser().getBirthDate() != null) {
				cb.add(Restrictions.eqOrIsNull("birthDate", request.getUser()
						.getBirthDate()));
			}
			if (request.getUser().getCity() != null
					&& !request.getUser().getCity().isEmpty()) {
				cb.add(Restrictions.eqOrIsNull("city", request.getUser()
						.getCity()));
			}
			if (request.getUser().getCountry() != null
					&& !request.getUser().getCountry().isEmpty()) {
				cb.add(Restrictions.eqOrIsNull("country", request.getUser()
						.getCountry()));
			}
			if (request.getUser().getPhone() != null
					&& !request.getUser().getPhone().isEmpty()) {
				cb.add(Restrictions.eqOrIsNull("phone", request.getUser()
						.getPhone()));
			}
			if (request.getUser().getGender() != null) {
				cb.add(Restrictions.eqOrIsNull("gender", request.getUser()
						.getGender()));
			}
			@SuppressWarnings("unchecked")
			List<User> users = cb.list();
			response = new UserListResponse(users);
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response = new UserListResponse(ErrorCode.INTERNAL_ERROR,
					"Can't search users");
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}
}
