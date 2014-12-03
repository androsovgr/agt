package ru.mephi.agt.api.request;

import java.util.List;

import ru.mephi.agt.request.gui.GuiRequest;

public class IdListGuiRequest extends GuiRequest {

	private List<Long> idList;

	public List<Long> getIdList() {
		return idList;
	}

	public void setIdList(List<Long> idList) {
		this.idList = idList;
	}

	public IdListGuiRequest() {
		super();
	}

	@Override
	public String toString() {
		return "IdListGuiRequest [idList=" + idList + ", ownId=" + ownId
				+ ", uid=" + uid + ", transactionId=" + transactionId + "]";
	}

	public IdListGuiRequest(long id, String uid, List<Long> idList) {
		super(id, uid);
		this.idList = idList;
	}

}
