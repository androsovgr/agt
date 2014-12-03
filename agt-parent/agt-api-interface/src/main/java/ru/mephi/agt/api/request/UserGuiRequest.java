package ru.mephi.agt.api.request;

import ru.mephi.agt.model.User;
import ru.mephi.agt.request.gui.GuiRequest;

public class UserGuiRequest extends GuiRequest {

	private User user;

	@Override
	public String toString() {
		return "UserGuiRequest [user=" + user + ", id=" + ownId + ", uid=" + uid
				+ ", transactionId=" + transactionId + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserGuiRequest() {
		super();
	}

	public UserGuiRequest(long id, String uid, User user) {
		super(id, uid);
		this.user = user;
	}

}
