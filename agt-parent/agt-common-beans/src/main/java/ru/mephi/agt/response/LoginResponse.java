package ru.mephi.agt.response;

import ru.mephi.agt.request.BaseResponse;
import ru.mephi.agt.util.ErrorCode;

public class LoginResponse extends BaseResponse {

	boolean success;

	public LoginResponse(ErrorCode errorCode, String errorMessage,
			boolean success) {
		super(errorCode, errorMessage);
		this.success = success;
	}

	@Override
	public String toString() {
		return "LoginResponse [success=" + success + ", errorCode=" + errorCode
				+ ", errorMessage=" + errorMessage + "]";
	}

	public LoginResponse(boolean success) {
		super();
		this.success = success;
	}

	public LoginResponse() {
		super();
	}

	public LoginResponse(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
