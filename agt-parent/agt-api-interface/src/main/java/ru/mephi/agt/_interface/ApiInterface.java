package ru.mephi.agt._interface;

import ru.mephi.agt._interface.request.LoginRequest;
import ru.mephi.agt._interface.response.LoginResponse;

public interface ApiInterface {

	public LoginResponse login(LoginRequest request);
	// TODO: fill
}
