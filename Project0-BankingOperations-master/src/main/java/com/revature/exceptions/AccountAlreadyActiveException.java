package com.revature.exceptions;

public class AccountAlreadyActiveException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AccountAlreadyActiveException() {
		super("Selected account is already active");
	}

}
