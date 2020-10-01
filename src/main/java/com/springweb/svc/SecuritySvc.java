package com.springweb.svc;

public interface SecuritySvc {

	String findLoggedInUsername();

	void autoLogin(String username, String password);
}
