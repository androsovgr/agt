package ru.mephi.agt.response;

import java.util.List;

import ru.mephi.agt.model.UserStatus;
import ru.mephi.agt.util.ErrorCode;

public class UserStatusListResponse extends BaseResponse {

	private List<UserStatus> userStatus;

	public UserStatusListResponse() {
		super();
	}

	public UserStatusListResponse(List<UserStatus> userStatus) {
		super();
		this.userStatus = userStatus;
	}

	public UserStatusListResponse(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	@Override
	public String toString() {
		return "UserStatusListResponse [userStatus=" + userStatus
				+ ", errorCode=" + errorCode + ", errorMessage=" + errorMessage
				+ "]";
	}

	public List<UserStatus> getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(List<UserStatus> userStatus) {
		this.userStatus = userStatus;
	}

}
