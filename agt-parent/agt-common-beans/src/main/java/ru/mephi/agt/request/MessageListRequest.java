package ru.mephi.agt.request;

import java.util.List;

import ru.mephi.agt.model.Message;

public class MessageListRequest extends BaseRequest {

	private List<Message> messages;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "MessageListRequest [messages=" + messages + ", transactionId="
				+ transactionId + "]";
	}

	public MessageListRequest() {
		super();
	}

	public MessageListRequest(List<Message> messages) {
		super();
		this.messages = messages;
	}

	public MessageListRequest(String transactionId, List<Message> messages) {
		super(transactionId);
		this.messages = messages;
	}

}
