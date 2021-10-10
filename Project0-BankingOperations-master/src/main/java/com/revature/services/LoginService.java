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
	

	
	public static Customer CustomerLogin() {
		
		String username,password;
		sc = new Scanner(System.in);
		System.out.print("\nInput your username and password: ");
		System.out.print("\nUsername: ");
		username=sc.nextLine();
		username = username.trim();
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
			//System.out.print("\nUser logged in sucessfully");  //Print on logger attempt to log in
		}
		else
			throw new IncorrectPasswordException();
		}
		catch (DatabaseConnectionFailedException e) {
			System.out.println(e.getMessage());
			Renderer.waitForInput();
			customer = null;
		}
		catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
			Renderer.waitForInput();
			customer = null;
		}
		catch (IncorrectPasswordException e) {
			System.out.println(e.getMessage());
			Renderer.waitForInput();
			customer = null;
		}
		catch(InputMismatchException e) {
			System.out.println("Input data does not correspond to fields");
			Renderer.waitForInput();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace(); Logger
			Renderer.waitForInput();
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
		username = username.trim();
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
			//System.out.print("\nUser logged in sucessfully");  //Print on logger attempt to log in
		}
		else
			throw new IncorrectPasswordException();
		}
		catch (DatabaseConnectionFailedException e) {
			System.out.println(e.getMessage());
			Renderer.waitForInput();
			employee = null;
		}
		catch(InputMismatchException e) {
			System.out.println("Input data does not correspond to fields");
			Renderer.waitForInput();
		}
		catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
			Renderer.waitForInput();
			employee = null;
		}
		catch (IncorrectPasswordException e) {
			System.out.println(e.getMessage());
			Renderer.waitForInput();
			employee = null;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace(); Logger
			Renderer.waitForInput();
			employee = null;
		}
		
		return employee;
		
	}
}
