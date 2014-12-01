package ru.mephi.agt.response;

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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
