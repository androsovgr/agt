package ru.mephi.agt.request.gui;

import ru.mephi.agt.model.Message;

public class MessageGuiRequest extends GuiRequest {

	protected Message message;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageGuiRequest [message=" + message + ", id=" + ownId
				+ ", uid=" + uid + ", transactionId=" + transactionId + "]";
	}

	public MessageGuiRequest() {
		super();
	}

	public MessageGuiRequest(String transactionId, long id, String uid,
			Message message) {
		super(transactionId, id, uid);
		this.message = message;
	}

}
