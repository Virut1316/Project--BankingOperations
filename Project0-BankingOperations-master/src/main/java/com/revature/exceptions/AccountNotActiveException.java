package com.revature.exceptions;

public class AccountNotActiveException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountNotActiveException() {
		super("This account has not been approved yet, please try later");
	}
}
