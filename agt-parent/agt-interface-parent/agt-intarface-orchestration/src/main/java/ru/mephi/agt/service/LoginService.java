package ru.mephi.agt.service;

import ru.mephi.agt.request.IdRequest;
import ru.mephi.agt.request.LoginRequest;
import ru.mephi.agt.request.StringRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.IdResponse;
import ru.mephi.agt.response.LoginResponse;

public interface LoginService {

	public LoginResponse tryLogin(LoginRequest request);

	public IdResponse register(StringRequest request);

	public BaseResponse logout(IdRequest request);
}
