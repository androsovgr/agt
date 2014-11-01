package ru.mephi.agt.api.response;

import java.util.List;

import ru.mephi.agt.model.Message;
import ru.mephi.agt.request.BaseResponse;
import ru.mephi.agt.util.ErrorCode;

public class MessageListResponse extends BaseResponse {

	private List<Message> messages;

	@Override
	public String toString() {
		return "MessageListResponse [messages=" + messages + ", errorCode="
				+ errorCode + ", errorMessage=" + errorMessage + "]";
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public MessageListResponse(List<Message> messages) {
		super();
		this.messages = messages;
	}

	public MessageListResponse(ErrorCode errorCode, String errorMessage,
			List<Message> messages) {
		super(errorCode, errorMessage);
		this.messages = messages;
	}

}
