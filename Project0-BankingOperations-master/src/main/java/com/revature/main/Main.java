package com.revature.main;
import java.io.IOException;

import com.revature.logger.LoggerManager;
import com.revature.model.Customer;
import com.revature.model.Employee;
import com.revature.services.*;
import com.revature.view.Renderer;


public class Main {

    // Serve as an entry point for the application and house static variables for application wide access.
    public static void main(String[] args)
    {
    	LoggerManager.logger.info("Bank app started");
    	Employee employee=null;//Starts with an unlogged user
    	Customer customer=null;//Starts with an unlogged user
    	boolean finish = false;
    	while(!finish) {
			Renderer.renderLoginMenu();
        	switch (OptionInput.choiceCatcher())
        	{
        	case 1:
            	LoggerManager.logger.info("Selected option 1 to log in as a customer");
        		customer = LoginService.CustomerLogin();
        		break;
        	case 2:
            	LoggerManager.logger.info("Selected option 2 to sign up  as a customer");
        		SignUpService.signUpWindow();
        		break;
        	case 3:
            	LoggerManager.logger.info("Selected option 1 to log in as a customer");
        		employee = LoginService.EmployeeLogin();
        		break;
        	case 4:
            	LoggerManager.logger.info("Selected option 4 to terminate session");
        		finish=true;
        		employee = null;
        		customer = null;
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
						LoggerManager.logger.info("Customer "+customer.getUsername()+" selected 1 to view accounts");
						CustomerOperationsService.ViewAccounts(customer.getId());
						break;
					case 2:
						LoggerManager.logger.info("Customer "+customer.getUsername()+" selected 2 to withdraw");
						CustomerOperationsService.withdrawFromAccount(customer.getId());
						break;
					case 3:
						LoggerManager.logger.info("Customer "+customer.getUsername()+" selected 3 to deposit");
						CustomerOperationsService.DepositToAccount(customer.getId());
						break;
					case 4:
						LoggerManager.logger.info("Customer "+customer.getUsername()+" selected 4 transfer");
						CustomerOperationsService.transferToAccount(customer.getId());
						break;
		        	case 5:
						LoggerManager.logger.info("Customer "+customer.getUsername()+" selected 5 to apply for new account");
		        		CustomerOperationsService.ApplyForAccount(customer.getId());
		        		break;
					case 6:
						LoggerManager.logger.info("Customer "+customer.getUsername()+" selected 6 to log out");
						finish=true;
		        		customer = null;
						break;
		        	default:
		        		System.out.println("\nPlease select a valid option, pick the number of the option you want to choose");
		        		break;
					}
        			
        			
        		}
        		finish = false;
        	}
        	else if(employee!=null&&!employee.isAdmin()) { // Selection of a employee
        		while(!finish) {
            		System.out.println("\nWelcome back "+employee.getUsername());
            		Renderer.renderEmployeeMenu();

        			switch (OptionInput.choiceCatcher()) {
					case 1:
						LoggerManager.logger.info("Employee "+employee.getUsername()+" selected 1 to Approve/deny accounts");
						EmployeeService.ApproveDenyAccount();
						break;
					
					case 2:
						LoggerManager.logger.info("Employee "+employee.getUsername()+" selected 2 to view an account");
						EmployeeService.viewAccount();
						break;
					case 3:
						LoggerManager.logger.info("Employee "+employee.getUsername()+" selected 3 to view a customer");
						EmployeeService.viewCustomer();
						break;
					case 4:
						LoggerManager.logger.info("Employee "+employee.getUsername()+" selected 4 to view all active accounts");
						EmployeeService.viewAllActiveAccounts();
						break;
					case 5:
						LoggerManager.logger.info("Employee "+employee.getUsername()+" selected 5 to view all inactive accounts");
						EmployeeService.viewAllInactiveAccounts();
						break;
					case 6:
						LoggerManager.logger.info("Employee "+employee.getUsername()+" selected 6 to log out");
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
        	else if(employee!=null&&employee.isAdmin()) { 
        		while(!finish) {
            		System.out.println("\nWelcome back "+employee.getUsername());
            		Renderer.renderAdminMenu();

        			switch (OptionInput.choiceCatcher()) {
					case 1:
						LoggerManager.logger.info("Employee "+employee.getUsername()+" selected 1 to Approve/deny accounts");
						EmployeeService.ApproveDenyAccount();
						break;
					
					case 2:
						LoggerManager.logger.info("Employee "+employee.getUsername()+" selected 2 to view an account");
						EmployeeService.viewAccount();
						break;
					case 3:
						LoggerManager.logger.info("Employee "+employee.getUsername()+" selected 3 to view a customer");
						EmployeeService.viewCustomer();
						break;
					case 4:
						LoggerManager.logger.info("Employee "+employee.getUsername()+" selected 4 to view all active accounts");
						EmployeeService.viewAllActiveAccounts();
						break;
					case 5:
						LoggerManager.logger.info("Employee "+employee.getUsername()+" selected 5 to view all inactive accounts");
						EmployeeService.viewAllInactiveAccounts();
						break;
					case 6:
						LoggerManager.logger.info("Employee "+employee.getUsername()+" selected 6 to Withdraw");
						EmployeeService.adminWithdraw();
						break;
					case 7:
						LoggerManager.logger.info("Employee "+employee.getUsername()+" selected 7 to Deposit");
						EmployeeService.adminDeposit();
						break;
					case 8:
						LoggerManager.logger.info("Employee "+employee.getUsername()+" selected 8 to Transfer");
						EmployeeService.adminTransfer();
						break;
					case 9:
						LoggerManager.logger.info("Employee "+employee.getUsername()+" selected 9 to cancel an account");
						EmployeeService.adminCancel();
						break;
					case 10:
						LoggerManager.logger.info("Employee "+employee.getUsername()+" selected 10 to log out");
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
        	
        	
        		
    	}
    	LoggerManager.logger.info("Bank app terminated");
		System.out.println("\nSee you soon!");

    }


}
