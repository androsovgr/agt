package ru.mephi.agt.response;

import ru.mephi.agt.model.User;
import ru.mephi.agt.util.ErrorCode;

public class UserResponse extends BaseResponse {

	private User user;

	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserResponse(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
		// TODO Auto-generated constructor stub
	}

	public UserResponse(User user) {
		super();
		this.user = user;
	}

	public UserResponse(ErrorCode errorCode, String errorMessage, User user) {
		super(errorCode, errorMessage);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserResponse [user=" + user + ", errorCode=" + errorCode
				+ ", errorMessage=" + errorMessage + "]";
	}

}
