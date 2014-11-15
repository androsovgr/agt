package ru.mephi.agt.request;

import ru.mephi.agt.model.User;

public class UserRequest extends BaseRequest {
	private User user;

	@Override
	public String toString() {
		return "UserRequest [user=" + user + ", transactionId=" + transactionId
				+ "]";
	}

	public UserRequest() {
		super();
	}

	public UserRequest(String transactionId) {
		super(transactionId);
	}

	public UserRequest(User user) {
		super();
		this.user = user;
	}

	public UserRequest(String transactionId, User user) {
		super(transactionId);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
