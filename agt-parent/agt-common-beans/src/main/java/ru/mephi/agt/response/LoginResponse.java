package ru.mephi.agt.response;

import ru.mephi.agt.util.ErrorCode;

public class LoginResponse extends BaseResponse {

	protected String uid;

	@Override
	public String toString() {
		return "LoginResponse [uid=" + uid + ", errorCode=" + errorCode
				+ ", errorMessage=" + errorMessage + "]";
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public LoginResponse() {
		super();
	}

	public LoginResponse(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public LoginResponse(String uid) {
		super();
		this.uid = uid;
	}

}
