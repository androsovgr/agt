package ru.mephi.agt.response;

import ru.mephi.agt.util.ErrorCode;

public class IdResponse extends BaseResponse {

	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public IdResponse() {
		super();
	}

	public IdResponse(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public IdResponse(long id) {
		super();
		this.id = id;
	}

	public IdResponse(ErrorCode errorCode, String errorMessage, long id) {
		super(errorCode, errorMessage);
		this.id = id;
	}

	@Override
	public String toString() {
		return "IdResponse [id=" + id + ", errorCode=" + errorCode
				+ ", errorMessage=" + errorMessage + "]";
	}

}
