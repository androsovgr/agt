package ru.mephi.agt.api.request;

import ru.mephi.agt.request.gui.GuiRequest;

public class IdGuiRequest extends GuiRequest {

	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "IdGuiRequest [id=" + id + ", uid=" + uid + ", transactionId="
				+ transactionId + "]";
	}

	public IdGuiRequest() {
		super();
	}

}
