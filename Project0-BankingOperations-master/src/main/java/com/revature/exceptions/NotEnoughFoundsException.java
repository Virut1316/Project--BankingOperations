package com.revature.exceptions;

public class NotEnoughFoundsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotEnoughFoundsException() {
		super("Insufficient funds to carry out the operation");
	}
}