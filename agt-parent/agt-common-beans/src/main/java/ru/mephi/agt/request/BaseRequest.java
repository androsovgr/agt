package ru.mephi.agt.request;

import java.util.UUID;

public class BaseRequest {

	protected String transactionId;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public BaseRequest() {
		super();
		transactionId = UUID.randomUUID().toString();
	}

	public BaseRequest(String transactionId) {
		super();
		this.transactionId = transactionId;
	}

}
