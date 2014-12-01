package ru.mephi.agt.api;

import javax.ejb.EJB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.agt.request.BaseRequest;
import ru.mephi.agt.request.LoginRequest;
import ru.mephi.agt.request.StringRequest;
import ru.mephi.agt.request.gui.GuiRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.IdResponse;
import ru.mephi.agt.response.LoginResponse;
import ru.mephi.agt.service.HazelcastService;
import ru.mephi.agt.service.LoginService;
import ru.mephi.agt.util.LogUtil;

public class ApiService implements ApiInterface {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ApiService.class);

	@EJB
	private LoginService loginService;

	@EJB
	private HazelcastService hazelcastService;

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

	@Override
	public BaseResponse test(BaseRequest request) {
		return hazelcastService.setLogined(new GuiRequest(1, "uid"));
	}

	@Override
	public BaseResponse test2(BaseRequest request) {
		return hazelcastService.checkLogined(new GuiRequest(1, "uid"));
	}

}
