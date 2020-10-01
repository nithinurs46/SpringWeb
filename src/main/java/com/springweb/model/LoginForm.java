package com.springweb.model;

public class LoginForm {
	
	private String userId;
	private String password;
	private String invalidCredentials;
	
	public LoginForm() {
	}
	
	public LoginForm(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getInvalidCredentials() {
		return invalidCredentials;
	}

	public void setInvalidCredentials(String invalidCredentials) {
		this.invalidCredentials = invalidCredentials;
	}
	
	

}
