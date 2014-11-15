package ru.mephi.agt.service;

import ru.mephi.agt.request.LoginRequest;
import ru.mephi.agt.request.StringRequest;
import ru.mephi.agt.response.IdResponse;
import ru.mephi.agt.response.LoginResponse;

public interface LoginServiceInterface {

	public LoginResponse tryLogin(LoginRequest request);

	public IdResponse register(StringRequest request);
}
