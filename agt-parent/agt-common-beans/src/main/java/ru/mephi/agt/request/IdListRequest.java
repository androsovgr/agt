package ru.mephi.agt.request;

import java.util.List;

public class IdListRequest extends BaseRequest {

	private List<Long> idList;

	public List<Long> getIdList() {
		return idList;
	}

	public void setIdList(List<Long> idList) {
		this.idList = idList;
	}

	public IdListRequest() {
		super();
	}

	public IdListRequest(String transactionId, List<Long> idList) {
		super(transactionId);
		this.idList = idList;
	}

	public IdListRequest(List<Long> idList) {
		super();
		this.idList = idList;
	}

	@Override
	public String toString() {
		return "IdListRequest [idList=" + idList + ", transactionId="
				+ transactionId + "]";
	}
	
}
