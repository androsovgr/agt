package ru.mephi.agt.response;

import ru.mephi.agt.util.ErrorCode;

public class BooleanResponse extends BaseResponse {

	private boolean bool;

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}

	@Override
	public String toString() {
		return "BooleanResponse [bool=" + bool + ", errorCode=" + errorCode
				+ ", errorMessage=" + errorMessage + "]";
	}

	public BooleanResponse(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public BooleanResponse() {
		super();
	}

	public BooleanResponse(boolean bool) {
		super();
		this.bool = bool;
	}

}
