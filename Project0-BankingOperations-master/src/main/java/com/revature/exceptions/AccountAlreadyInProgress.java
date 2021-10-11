package com.revature.exceptions;

public class AccountAlreadyInProgress extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public AccountAlreadyInProgress() {
		super("An extra account is alredy waiting for approval");
	}
}
