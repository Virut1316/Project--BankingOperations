package com.revature.services;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.dao.CustomerDao;
import com.revature.model.Customer;
import com.revature.view.Renderer;

public class SignUpService {
private static Scanner sc;

	
	public static boolean signUpWindow() {
		CustomerDao customerDao = new CustomerDao();
		boolean success = false;

		try {
			sc = new Scanner(System.in);
			
			System.out.println("Please fill out your information to apply for an account");
			String firstName,lastName,email,username,password;
			System.out.print("First Name:");
			firstName=sc.nextLine();
			firstName = firstName.trim();
			System.out.print("Last Name:");
			lastName = sc.nextLine();
			lastName=lastName.trim();
			System.out.print("Email:");
			email = sc.nextLine();
			email = email.trim();
			System.out.print("Username:");
			username = sc.nextLine();
			username = username.trim();
			System.out.print("Password:");
			password = sc.nextLine();
			
			success = customerDao.insertElement(new Customer(firstName,lastName,email,username,password));
			
			
			if(success)
				System.out.println("Congratulations you have applied successfully for an account, please wait until a bank employee approve your application");
			else
				System.out.println("Something went wrong with the application, please try again later");

				
		}catch(InputMismatchException e) {
			System.out.println("Input data does not correspond to fields");
		}catch (Exception e) {
			//Logger an error related with the input information
		}
		
		Renderer.waitForInput();
		return success;
	
	}
}
