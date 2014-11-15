package ru.mephi.agt.api;

import javax.ejb.EJB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.request.LoginRequest;
import ru.mephi.agt.request.StringRequest;
import ru.mephi.agt.response.IdResponse;
import ru.mephi.agt.response.LoginResponse;
import ru.mephi.agt.service.LoginServiceInterface;
import ru.mephi.agt.util.LogUtil;

public class ApiService implements ApiInterface {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ApiService.class);

	@EJB
	private LoginServiceInterface loginService;

	public ApiService() {
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

}
