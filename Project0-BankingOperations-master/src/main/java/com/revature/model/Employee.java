package com.revature.model;

public class Employee extends User {

	private int id;
	private boolean admin;
	
	public Employee() {
		super();
	}
	
	public Employee(int id,boolean admin, String username, String password) {
		super(username, password);
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
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

	@Override
	public String toString() {
		return "Employee [admin=" + admin + ", Username=" + getUsername() + ", Password=" + getPassword()+"]";
	}
	
	
	
}
