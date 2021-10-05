package com.revature.model;

public class Employee extends User {

	private boolean admin;
	
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean ApproveAccount(){
		//Logic to approve an account
		return true;
	}
	/*public Account DetailedAccount(){
		//Logic to search and return an account
		return Account;
	}*/
	public boolean CancelAccount(){
		//Logic to cancel an account if its admin
		return true;
	}
	
	//Also override WDT methods if necesary to admin
	
	
}
