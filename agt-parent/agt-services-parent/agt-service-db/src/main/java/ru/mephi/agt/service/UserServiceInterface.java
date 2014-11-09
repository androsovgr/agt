package ru.mephi.agt.service;

import ru.mephi.agt.request.IdRequest;
import ru.mephi.agt.response.UserResponse;

public interface UserServiceInterface {

	public UserResponse getUserById(IdRequest request);
}
