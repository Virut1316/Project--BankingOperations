package com.revature.exceptions;

public class AccountDoesNotExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public AccountDoesNotExistsException() {
		super("Such account does not exist or is not reachable");
	}
}
