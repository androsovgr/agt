package ru.mephi.agt.model;

public class UserStatus {

	private long userId;
	private Status status;

	public UserStatus() {
		super();
	}

	public long getUserId() {
		return userId;
	}

	public Status getStatus() {
		return status;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public UserStatus(long userId, Status status) {
		super();
		this.userId = userId;
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserStatus [userId=" + userId + ", status=" + status + "]";
	}

}
