package com.revature.exceptions;

public class DatabaseConnectionFailedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseConnectionFailedException() {
		super("Connection with database failed, please try again later");
	}
}
