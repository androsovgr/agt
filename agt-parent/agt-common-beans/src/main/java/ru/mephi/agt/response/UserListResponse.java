package ru.mephi.agt.response;

import java.util.List;

import ru.mephi.agt.model.User;
import ru.mephi.agt.util.ErrorCode;

public class UserListResponse extends BaseResponse {

	private List<User> users;

	@Override
	public String toString() {
		return "UserListResponse [users=" + users + ", errorCode=" + errorCode
				+ ", errorMessage=" + errorMessage + "]";
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public UserListResponse() {
		super();
	}

	public UserListResponse(List<User> users) {
		super();
		this.users = users;
	}

	public UserListResponse(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

}
