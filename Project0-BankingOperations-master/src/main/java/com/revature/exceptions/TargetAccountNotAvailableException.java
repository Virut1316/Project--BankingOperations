package com.revature.exceptions;

public class TargetAccountNotAvailableException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TargetAccountNotAvailableException(){
		super("Target account not available or inactive");
	}
}
