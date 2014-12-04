package ru.mephi.agt.api.request;

import ru.mephi.agt.request.gui.GuiRequest;

public class SendMessageGuiRequest extends GuiRequest {

	private long receiverId;
	private String message;

	@Override
	public String toString() {
		return "SendMessageGuiRequest [receiverId=" + receiverId + ", message="
				+ message + ", ownId=" + ownId + ", uid=" + uid
				+ ", transactionId=" + transactionId + "]";
	}

	public long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SendMessageGuiRequest() {
		super();
	}

	public SendMessageGuiRequest(long id, String uid, long receiverId,
			String message) {
		super(id, uid);
		this.receiverId = receiverId;
		this.message = message;
	}

}
