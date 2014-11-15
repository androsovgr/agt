package ru.mephi.agt.request;


public class LoginRequest extends BaseRequest {

	private long id;
	private String password;

	public LoginRequest(long id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	public LoginRequest() {
		super();
	}

	public LoginRequest(String transactionId) {
		super(transactionId);
	}

	public LoginRequest(String transactionId, long id, String password) {
		super(transactionId);
		this.id = id;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequest [id=" + id + ", password=" + password
				+ ", transactionId=" + transactionId + "]";
	}

}
