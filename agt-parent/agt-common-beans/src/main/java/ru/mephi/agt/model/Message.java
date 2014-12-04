package ru.mephi.agt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message_queue")
public class Message {

	@Id
	@Column(name = "message_id")
	private long messageId;
	@Column(name = "message_time")
	private Date messageTime;
	@Column(name = "message")
	private String message;
	@Column(name = "message_sender")
	private long messageSender;
	@Column(name = "message_receiver")
	private long messageReceiver;

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", messageTime="
				+ messageTime + ", message=" + message + ", messageSender="
				+ messageSender + ", messageReceiver=" + messageReceiver + "]";
	}

	public Message(long messageId, Date messageTime, String message,
			long messageSender, long messageReceiver) {
		super();
		this.messageId = messageId;
		this.messageTime = messageTime;
		this.message = message;
		this.messageSender = messageSender;
		this.messageReceiver = messageReceiver;
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
