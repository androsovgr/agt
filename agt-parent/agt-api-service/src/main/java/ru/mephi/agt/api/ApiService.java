package ru.mephi.agt.api;

import javax.ejb.EJB;

import ru.mephi.agt.request.LoginRequest;
import ru.mephi.agt.response.LoginResponse;
import ru.mephi.agt.service.LoginServiceInterface;

public class ApiService implements ApiInterface {

	@EJB
	private LoginServiceInterface loginService;

	public ApiService() {
	}

	@Override
	public LoginResponse login(LoginRequest request) {
		return loginService.tryLogin(request);
	}

}
