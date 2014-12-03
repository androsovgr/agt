package ru.mephi.agt.request;

public class ObjectRequest extends BaseRequest {

	private Object object;

	@Override
	public String toString() {
		return "ObjectRequest [object=" + object + ", transactionId="
				+ transactionId + "]";
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public ObjectRequest(Object object) {
		super();
		this.object = object;
	}

}
