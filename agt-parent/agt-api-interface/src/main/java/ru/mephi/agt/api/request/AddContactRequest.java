package ru.mephi.agt.api.request;

import ru.mephi.agt.request.gui.GuiRequest;

public class AddContactRequest extends GuiRequest {

	private long userId;
	private String displayName;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public AddContactRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddContactRequest(long userId, String displayName) {
		super();
		this.userId = userId;
		this.displayName = displayName;
	}

}
