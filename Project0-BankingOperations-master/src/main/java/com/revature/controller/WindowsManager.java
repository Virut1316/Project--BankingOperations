package com.revature.controller;

import com.revature.dao.EmployeeDao;
import com.revature.model.Customer;
import com.revature.model.Employee;
import com.revature.view.Renderer;
import java.util.Scanner;

public class WindowsManager {
	private static Scanner sc;
	
	public static int loginWindow() {
		//
		sc = new Scanner(System.in);
		Renderer.renderScreen("Login Menu");
		
		System.out.print("Your selection: ");
		return sc.nextInt();
		
	}
	/*
	public static Customer CustomerLogin() {
		
		String username,password;
		sc = new Scanner(System.in);
		System.out.print("\nInput your username and password: ");
		System.out.print("\nUsername: ");
		sc.nextLine();
		System.out.print("Password: ");
		sc.nextLine();
		
		Customer customer = new EmployeeDao().getElementByUsername(username);
		
		if(customer==null)
			System.out.print("\nUsername does not exists: ");
		else if (customer.getPassword().equals(password))
			System.out.print("\nWrong credentials ");
		else
			System.out.print("\nUser logged in sucessfully");
		
		return customer;
		
	}*/
	
	public static Employee EmployeeLogin() {
		
		String username,password;
		sc = new Scanner(System.in);
		System.out.print("\nInput your username and password: ");
		System.out.print("\nUsername: ");
		username=sc.nextLine();
		System.out.print("Password: ");
		password=sc.nextLine();
		
		Employee employee = new EmployeeDao().getElementByUsername(username);
	
		if(employee==null)
			System.out.print("\nUsername does not exists: ");
		else if (employee.getPassword().equals(password))
			System.out.print("\nUser logged in sucessfully");
		else
			System.out.print("\nWrong credentials ");
			
		
		return employee;
		
	}

}
