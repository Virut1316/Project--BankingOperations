package com.revature.services;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.dao.CustomerDao;
import com.revature.dao.EmployeeDao;
import com.revature.exceptions.DatabaseConnectionFailedException;
import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.model.Customer;
import com.revature.model.Employee;
import com.revature.view.Renderer;

public class LoginService {
	private static Scanner sc;
	
	public static int loginWindow() {
		
		int choice;
		try {
			sc = new Scanner(System.in);
			Renderer.renderLoginMenu();
			
			System.out.print("Your selection: ");
			choice = sc.nextInt();
		}catch (InputMismatchException e) {
			choice = 0;
		}
		return choice;
	
	}
	
	public static Customer CustomerLogin() {
		
		String username,password;
		sc = new Scanner(System.in);
		System.out.print("\nInput your username and password: ");
		System.out.print("\nUsername: ");
		username=sc.nextLine();
		System.out.print("Password: ");
		password=sc.nextLine();
		
		Customer customer = new CustomerDao().getElementByUsername(username);
		
		try {
			
		if(customer==null)
			throw new DatabaseConnectionFailedException();
		else if (customer.getPassword()==null) {
			throw new UserNotFoundException();
		}	
		else if (customer.getPassword().equals(password)) {
			System.out.print("\nUser logged in sucessfully");
		}
		else
			throw new IncorrectPasswordException();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			customer = null;
		}
		
		return customer;
		
	}
	
	public static Employee EmployeeLogin() {
		
		String username,password;
		sc = new Scanner(System.in);
		System.out.print("\nInput your username and password: ");
		System.out.print("\nUsername: ");
		username=sc.nextLine();
		System.out.print("Password: ");
		password=sc.nextLine();
		
		
		Employee employee = new EmployeeDao().getElementByUsername(username);
		
		try {
			
		if(employee==null)
			throw new DatabaseConnectionFailedException();
		else if (employee.getPassword()==null) {
			throw new UserNotFoundException();
		}	
		else if (employee.getPassword().equals(password)) {
			System.out.print("\nUser logged in sucessfully");
		}
		else
			throw new IncorrectPasswordException();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			employee = null;
		}
		
		return employee;
		
	}
}
