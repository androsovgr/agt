package ru.mephi.agt.service;

import ru.mephi.agt.request.IdRequest;
import ru.mephi.agt.request.UserRequest;
import ru.mephi.agt.response.BaseResponse;
import ru.mephi.agt.response.IdResponse;
import ru.mephi.agt.response.UserListResponse;
import ru.mephi.agt.response.UserResponse;

public interface UserService {

	public UserResponse getUserById(IdRequest request);

	public IdResponse addUser(UserRequest request);

	public UserListResponse searchUsers(UserRequest request);

	public BaseResponse updateUser(UserRequest request);
}
