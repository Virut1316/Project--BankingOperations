package com.revature.exceptions;

public class InactiveAccountsNotAvailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InactiveAccountsNotAvailableException() {
		super("No inactive accounts were found");	
	}
	
	
}
