package ru.mephi.agt._interface.request;

import ru.mephi.agt.model.User;

public class LoginRequest extends BaseRequest {

	private User user;

	public LoginRequest() {
		super();
	}

	@Override
	public String toString() {
		return "LoginRequest [user=" + user + ", transactionId="
				+ transactionId + "]";
	}

	public LoginRequest(User user) {
		super();
		this.user = user;
	}

	public LoginRequest(String transactionId) {
		super(transactionId);
	}

	public User getUser() {
		return user;
	}

	public LoginRequest(String transactionId, User user) {
		super(transactionId);
		this.user = user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}