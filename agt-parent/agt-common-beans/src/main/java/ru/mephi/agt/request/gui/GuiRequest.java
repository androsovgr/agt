package ru.mephi.agt.request.gui;

import ru.mephi.agt.request.BaseRequest;

public class GuiRequest extends BaseRequest {

	protected long id;
	protected String uid;

	public GuiRequest(String transactionId, long id, String uid) {
		super(transactionId);
		this.id = id;
		this.uid = uid;
	}

	public GuiRequest(long id, String uid) {
		super();
		this.id = id;
		this.uid = uid;
	}

	public GuiRequest() {
		super();
	}

	public GuiRequest(String transactionId) {
		super(transactionId);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "GuiRequest [id=" + id + ", uid=" + uid + ", transactionId="
				+ transactionId + "]";
	}

}
