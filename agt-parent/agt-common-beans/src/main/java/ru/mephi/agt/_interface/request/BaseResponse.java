package ru.mephi.agt._interface.request;

import ru.mephi.agt.util.ErrorCode;

public class BaseResponse {

	protected ErrorCode errorCode;
	protected String errorMessage;

	@Override
	public String toString() {
		return "BaseResponse [errorCode=" + errorCode + ", errorMessage="
				+ errorMessage + "]";
	}

	public BaseResponse() {
		super();
		errorCode = ErrorCode.OK;
	}

	public BaseResponse(ErrorCode errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

}
