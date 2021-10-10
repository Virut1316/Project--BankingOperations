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
			Renderer.renderLoginMenu();
        	switch (OptionInput.choiceCatcher())
        	{
        	case 1:
        		customer = LoginService.CustomerLogin();
        		break;
        	case 2:
        		SignUpService.signUpWindow();
        		break;
        	case 3:
        		employee = LoginService.EmployeeLogin();
        		if(employee!=null)
        		System.out.println("\nWelcome back "+employee.getUsername());
        		break;
        	case 4:
        		finish=true;
        		break;
        	default:
        		System.out.println("\nPlease select a valid option, pick the number of the option you want to choose");
        		Renderer.waitForInput();
        		break;
        	}
        	
        	if (customer!=null) {
        		if(!CustomerOperationsService.checkActive(customer.getId())) {
        			finish=true;
        		}
        		while(!finish) {
        			System.out.println("\nWelcome back "+customer.getFirstName()+" "+customer.getLastName());
        			Renderer.renderMainMenu();
        			switch (OptionInput.choiceCatcher()) {
					case 1:
						CustomerOperationsService.ViewAccounts(customer.getId());
						break;
					case 2:
						CustomerOperationsService.withdrawFromAccount(customer.getId());
						break;
					case 3:
						CustomerOperationsService.DepositToAccount(customer.getId());
						break;
					case 4:
						CustomerOperationsService.transferToAccount(customer.getId());
						break;
					case 5:
						finish=true;
		        		customer = null;
						break;
		        	default:
		        		System.out.println("\nPlease select a valid option, pick the number of the option you want to choose");
		        		break;
					}
        			
        			
        		}
        		finish = false;
    			//System.out.print("User logged out"); logger
        	}
        	else if(employee!=null&&!employee.isAdmin()) { // Selection of a employee
        		while(!finish) {
            		Renderer.renderEmployeeMenu();

        			switch (OptionInput.choiceCatcher()) {
					case 1:
						EmployeeService.ApproveAccount();
						break;
					
					case 2:
						EmployeeService.viewAccount();
						break;
					case 3:
						EmployeeService.viewAllActiveAccounts();
						break;
					case 4:
						EmployeeService.viewAllInactiveAccounts();
						break;
					case 5:
						finish = true;
						employee =null;
						break;
					default:
		        		System.out.println("\nPlease select a valid option, pick the number of the option you want to choose");
		        		break;
					}
        			
        			
        			
        			
        		}
        		finish=false;
        	}
        	else if(employee!=null&&employee.isAdmin()) { //Selection of a logged admin
        		
        		
        	}
        	
        	
        		
    	} 	
		System.out.println("\nSee you soon!");

    }


}
