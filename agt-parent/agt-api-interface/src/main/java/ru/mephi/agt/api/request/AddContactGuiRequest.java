package ru.mephi.agt.api.request;

import ru.mephi.agt.request.gui.GuiRequest;

public class AddContactGuiRequest extends GuiRequest {

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

	public AddContactGuiRequest() {
		super();
	}

	public AddContactGuiRequest(long id, String uid, long userId,
			String displayName) {
		super(id, uid);
		this.userId = userId;
		this.displayName = displayName;
	}

}
