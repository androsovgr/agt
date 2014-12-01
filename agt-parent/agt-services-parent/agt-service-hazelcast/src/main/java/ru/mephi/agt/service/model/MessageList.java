package ru.mephi.agt.service.model;

import java.util.ArrayList;
import java.util.List;

import ru.mephi.agt.model.Message;

public class MessageList {

	private List<Message> messages = new ArrayList<Message>();

	@Override
	public String toString() {
		return "MessageList [messages=" + messages + "]";
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public MessageList(List<Message> messages) {
		super();
		this.messages = messages;
	}

	public MessageList() {
		super();
	}

}
