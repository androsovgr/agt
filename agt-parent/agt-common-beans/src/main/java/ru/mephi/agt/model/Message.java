package ru.mephi.agt.model;

import java.util.Date;

public class Message {

	private long messageId;
	private Date messageTime;
	private String message;
	private long messageSender;
	private long messageReceiver;

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", messageTime="
				+ messageTime + ", message=" + message + ", messageSender="
				+ messageSender + ", messageReceiver=" + messageReceiver + "]";
	}

	public Message() {
		super();
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public Date getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Date messageTime) {
		this.messageTime = messageTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(long messageSender) {
		this.messageSender = messageSender;
	}

	public long getMessageReceiver() {
		return messageReceiver;
	}

	public void setMessageReceiver(long messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

}
