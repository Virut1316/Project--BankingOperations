package com.revature.exceptions;

public class IncorrectMoneyFormatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IncorrectMoneyFormatException() {
		super("Money format is not correct, please imput the amount in the format (xx.xx)");
	}

}
