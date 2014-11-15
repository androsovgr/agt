package ru.mephi.agt.request;

public class StringRequest extends BaseRequest {

	private String string;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return "StringRequest [string=" + string + ", transactionId="
				+ transactionId + "]";
	}

	public StringRequest(String string) {
		super();
		this.string = string;
	}

	public StringRequest() {
		super();
	}

	public StringRequest(String transactionId, String string) {
		super(transactionId);
		this.string = string;
	}

}
