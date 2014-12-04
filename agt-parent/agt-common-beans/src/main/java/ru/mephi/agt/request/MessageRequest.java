package ru.mephi.agt.request;

import ru.mephi.agt.model.Message;

public class MessageRequest extends BaseRequest {

	private Message message;

	@Override
	public String toString() {
		return "MessageRequest [message=" + message + ", transactionId="
				+ transactionId + "]";
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public MessageRequest() {
		super();
	}

	public MessageRequest(Message message) {
		super();
		this.message = message;
	}

	public MessageRequest(String transactionId, Message message) {
		super(transactionId);
		this.message = message;
	}

}
