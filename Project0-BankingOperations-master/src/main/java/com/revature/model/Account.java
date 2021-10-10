package com.revature.model;


public class Account {
	
	private int accountNumber;
	private boolean active;
	private int balance;
	
	public Account(int accountNumber,boolean active, int balance) {
		this.accountNumber = accountNumber;
		this.active = active;
		this.balance = balance;
	}



	public Account() {
		//Called when no elements where found
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}



	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}

	
	
}
