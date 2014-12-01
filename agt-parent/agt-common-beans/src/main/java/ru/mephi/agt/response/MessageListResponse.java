package ru.mephi.agt.response;

import java.util.ArrayList;
import java.util.List;

import ru.mephi.agt.model.Message;
import ru.mephi.agt.util.ErrorCode;

public class MessageListResponse extends BaseResponse {

	protected List<Message> messages;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "MessageListResponse [messages=" + messages + ", errorCode="
				+ errorCode + ", errorMessage=" + errorMessage + "]";
	}

	public MessageListResponse() {
		super();
		messages = new ArrayList<Message>();
	}

	public MessageListResponse(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public MessageListResponse(List<Message> messages) {
		super();
		this.messages = messages;
	}

}
