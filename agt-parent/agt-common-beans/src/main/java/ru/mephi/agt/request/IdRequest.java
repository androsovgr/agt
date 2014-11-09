package ru.mephi.agt.request;

public class IdRequest extends BaseRequest {

	private long id;

	@Override
	public String toString() {
		return "IdRequest [id=" + id + ", transactionId=" + transactionId + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public IdRequest(long id) {
		super();
		this.id = id;
	}

	public IdRequest(String transactionId, long id) {
		super(transactionId);
		this.id = id;
	}

	public IdRequest() {
		super();
	}

	public IdRequest(String transactionId) {
		super(transactionId);
	}

}
