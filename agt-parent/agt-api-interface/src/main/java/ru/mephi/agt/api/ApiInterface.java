package ru.mephi.agt.api;

import ru.mephi.agt.api.request.LoginRequest;
import ru.mephi.agt.api.response.LoginResponse;

public interface ApiInterface {

	public LoginResponse login(LoginRequest request);
	// TODO: fill
}
