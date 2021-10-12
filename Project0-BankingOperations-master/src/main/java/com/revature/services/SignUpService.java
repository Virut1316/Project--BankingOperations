package com.revature.services;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.dao.CustomerDao;
import com.revature.exceptions.IncorrectFormatException;
import com.revature.logger.LoggerManager;
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
			if(firstName.length()<=0) throw new IncorrectFormatException();
			System.out.print("Last Name:");
			lastName = sc.nextLine();
			lastName=lastName.trim();
			if(lastName.length()<=0) throw new IncorrectFormatException();
			System.out.print("Email:");
			email = sc.nextLine();
			email = email.trim();
			if(email.length()<=0) throw new IncorrectFormatException();
			System.out.print("Username:");
			username = sc.nextLine();
			username = username.trim();
			if(username.length()<=0) throw new IncorrectFormatException();
			System.out.print("Password:");
			password = sc.nextLine();
			if(password.length()<=0) throw new IncorrectFormatException();
			
			success = customerDao.insertElement(new Customer(firstName,lastName,email,username,password));
			
			
			if(success) {
				LoggerManager.logger.info("New user was registered: "+username);
				System.out.println("Congratulations you have applied successfully for an account, please wait until a bank employee approve your application");
			}
			else {
				LoggerManager.logger.info("Failed to register user: "+username);
				System.out.println("Something went wrong with the application, please try again later");

			}

				
		}catch(InputMismatchException e) {
			System.out.println("Input data does not correspond to fields");
			LoggerManager.logger.warn("User tried to input not valid data : "+e.getMessage());
		
		}
		catch(IncorrectFormatException e) {
			System.out.println(e.getMessage());
			LoggerManager.logger.warn(e.getMessage());
		}
		catch (Exception e) {
			LoggerManager.logger.warn(e.getMessage());
		}
		
		Renderer.waitForInput();
		return success;
	
	}
}
