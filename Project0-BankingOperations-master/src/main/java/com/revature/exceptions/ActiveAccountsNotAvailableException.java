package com.revature.exceptions;

public class ActiveAccountsNotAvailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ActiveAccountsNotAvailableException() {
		super("No active accounts were found");	
	}
	
	
}
