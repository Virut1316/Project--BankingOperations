package com.revature.model;


public class Customer extends User {

	private String firstName;
	private String lastName;
	private String email;
	//private List<Account> accounts = Load accounts;
	
	public Customer() {
		super();
	}
	public Customer(String firstName, String lastName, String email,String username, String password) {
		super(username,password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
}
