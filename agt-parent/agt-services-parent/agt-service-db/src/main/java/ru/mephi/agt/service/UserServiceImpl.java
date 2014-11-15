package ru.mephi.agt.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.model.User;
import ru.mephi.agt.request.IdRequest;
import ru.mephi.agt.request.UserRequest;
import ru.mephi.agt.response.IdResponse;
import ru.mephi.agt.response.UserResponse;
import ru.mephi.agt.service.util.DbConnector;
import ru.mephi.agt.service.util.mapper.UserMapper;
import ru.mephi.agt.util.ErrorCode;
import ru.mephi.agt.util.LogUtil;

@Stateless
@Local(UserServiceInterface.class)
public class UserServiceImpl implements UserServiceInterface {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Override
	public UserResponse getUserById(IdRequest request) {
		final String methodName = "getUserById";
		LogUtil.logStarted(LOGGER, methodName, request);
		UserResponse response = new UserResponse();
		try (Connection con = DbConnector.getConnection();
				PreparedStatement ps = con
						.prepareStatement("SELECT * FROM USER WHERE user_id=?");) {
			ps.setLong(1, request.getId());
			ResultSet rs = ps.executeQuery();
			User user = UserMapper.parse(rs);
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
		try (Connection con = DbConnector.getConnection();
				PreparedStatement ps = con
						.prepareStatement(
								"INSERT INTO user VALUES(0,NULL,NULL,NULL,?,NULL,NULL,NULL,NULL,1,1)",
								Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, request.getUser().getPassword());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				long id = rs.getLong(1);
				response.setId(id);
			} else {
				response.setErrorCode(ErrorCode.INTERNAL_ERROR);
			}
		} catch (Exception e) {
			LogUtil.logError(LOGGER, methodName, request, e);
			response.setErrorCode(ErrorCode.INTERNAL_ERROR);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}
}
