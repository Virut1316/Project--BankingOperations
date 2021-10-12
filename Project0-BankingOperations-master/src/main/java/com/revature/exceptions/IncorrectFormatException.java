package com.revature.exceptions;

public class IncorrectFormatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncorrectFormatException() {
		super("Field cannot be empty");
	}
	
}
