package com.revature.model;

public abstract class User {
	
	private String username;
	private String password;
	
	User(){
		
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
			return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	//Every user uses this
	public boolean Withdraw() {
		//Add logic to withdraw from account
		return true;
	}
	public boolean Deposit() {
		//Add logic to deposit from account
		return true;
	}
	public boolean Tranfer() {
		//Add logic to transfer from account
		return true;
	}
	
	

}
