package com.revature.main;
import java.io.IOException;

import com.revature.model.Customer;
import com.revature.model.Employee;
import com.revature.services.*;
import com.revature.view.Renderer;


public class Main {

    // Serve as an entry point for the application and house static variables for application wide access.
    public static void main(String[] args)
    {
    	Employee employee=null;//Starts with an unlogged user
    	Customer customer=null;//Starts with an unlogged user
    	boolean finish = false;
    	
    	while(!finish) {
        	switch (LoginService.loginWindow())
        	{
        	case 1:
        		customer = LoginService.CustomerLogin();
        		if(customer!=null)
        		System.out.println("\nWelcome back "+customer.getFirstName()+" "+customer.getLastName());
        		break;
        	case 2:
        		SignUpService.signUpWindow();
        		break;
        	case 3:
        		employee = LoginService.EmployeeLogin();
        		if(employee!=null)
        		System.out.println("\nWelcome back "+employee.getUsername());
        		break;
        	default:
        		System.out.println("\nPlease select a valid option, pick the number of the option you want to choose");
        		break;
        	}
        	
        	if(customer!=null) {
        		Renderer.renderMainMenu();
        		finish=true;;
        	}
        	else if(employee!=null) {
        		Renderer.renderEmployeeMenu();
        		finish=true;;
        	}
        		
    	} 	
        
    }


}
