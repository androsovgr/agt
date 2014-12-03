package ru.mephi.agt.request.gui;

import ru.mephi.agt.request.BaseRequest;

public class GuiRequest extends BaseRequest {

	protected long ownId;
	protected String uid;

	public GuiRequest(String transactionId, long id, String uid) {
		super(transactionId);
		this.ownId = id;
		this.uid = uid;
	}

	public GuiRequest(long id, String uid) {
		super();
		this.ownId = id;
		this.uid = uid;
	}

	public GuiRequest() {
		super();
	}

	public GuiRequest(String transactionId) {
		super(transactionId);
	}

	public long getOwnId() {
		return ownId;
	}

	public void setOwnId(long id) {
		this.ownId = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "GuiRequest [id=" + ownId + ", uid=" + uid + ", transactionId="
				+ transactionId + "]";
	}

}
