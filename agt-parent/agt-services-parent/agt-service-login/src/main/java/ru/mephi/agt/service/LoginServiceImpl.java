package ru.mephi.agt.service;

import javax.ejb.Local;
import javax.ejb.Stateful;

import ru.mephi.agt.request.LoginRequest;
import ru.mephi.agt.response.LoginResponse;

@Stateful
@Local(LoginServiceInterface.class)
public class LoginServiceImpl implements LoginServiceInterface {

	@Override
	public LoginResponse tryLogin(LoginRequest request) {
		LoginResponse response = new LoginResponse();

		return response;
	}

}
