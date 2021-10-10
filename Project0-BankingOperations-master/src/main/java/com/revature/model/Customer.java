package com.revature.model;


public class Customer extends User {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	//private List<Account> accounts = Load accounts;
	
	public Customer() {
		super();
	}
	public Customer(int id, String firstName, String lastName, String email,String username, String password) {
		super(username,password);
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	public Customer(String firstName, String lastName, String email,String username, String password) { //This constructor is used for inserts, where id is not used
		super(username,password);
		this.id = -1;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public int getId() {
		return id;
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
