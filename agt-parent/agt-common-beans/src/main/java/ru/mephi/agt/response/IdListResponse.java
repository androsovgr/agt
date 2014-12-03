package ru.mephi.agt.response;

import java.util.List;

import ru.mephi.agt.util.ErrorCode;

public class IdListResponse extends BaseResponse {

	private List<Long> idList;

	@Override
	public String toString() {
		return "IdListResponse [idList=" + idList + "]";
	}

	public IdListResponse() {
		super();
	}

	public IdListResponse(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public IdListResponse(List<Long> idList) {
		super();
		this.idList = idList;
	}

	public List<Long> getIdList() {
		return idList;
	}

	public void setIdList(List<Long> idList) {
		this.idList = idList;
	}

}
