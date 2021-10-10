package com.revature.services;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.exceptions.AccountAlreadyActiveException;
import com.revature.exceptions.AccountDoesNotExistsException;
import com.revature.exceptions.ActiveAccountsNotAvailableException;
import com.revature.exceptions.DatabaseConnectionFailedException;
import com.revature.model.Account;
import com.revature.view.Renderer;

public class EmployeeService {

	private static Scanner sc;
	
public static void ApproveAccount() {
		
		AccountDao accounDao = new AccountDao();
		sc = new Scanner(System.in);
		String choice;
		
		try {
		
		System.out.print("Account number to approve: ");
		int accountId = sc.nextInt();
			
		Account account = accounDao.getAccount(accountId);

		if(account==null)
			throw new DatabaseConnectionFailedException();
		else if(account.getAccountNumber()==0)
			throw new AccountDoesNotExistsException();
		else if (account.isActive())
			throw new AccountAlreadyActiveException();
		
		System.out.println("This account is going to be activated ");
		Renderer.renderAccount(account);
		System.out.print("Proceed? (Y/n): ");
		choice = sc.next();
		
		if(choice.equals("Y")||choice.equals("y"))
			accounDao.updateAccount(new Account(account.getAccountNumber(),true,account.getBalance()));
		else 
			System.out.println("Operation cancelled");
		
		
		}catch (DatabaseConnectionFailedException e) {
			System.out.println(e.getMessage());
		}catch (AccountAlreadyActiveException e) {
			System.out.println(e.getMessage());
		}catch(InputMismatchException e) {
			System.out.println("Input data does not correspond to fields");
		}
		catch (AccountDoesNotExistsException e) {
			System.out.println(e.getMessage());
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			//e.getStackTrace() logger
		}
		Renderer.waitForInput();
		
	}
	
	
	public static void viewAccount() {
		
		AccountDao accounDao = new AccountDao();
		sc = new Scanner(System.in);
		try {
		
		System.out.print("Account number to see details: ");
		int accountId = sc.nextInt();
			
		Account account = accounDao.getAccount(accountId);
		
		if(account==null)
			throw new DatabaseConnectionFailedException();
		else if(account.getAccountNumber()==0)
			throw new AccountDoesNotExistsException();
		
		Renderer.renderAccount(account);
		
		}catch (DatabaseConnectionFailedException e) {
			System.out.println(e.getMessage());
		}catch(InputMismatchException e) {
			System.out.println("Input data does not correspond to fields");
		}
		catch (AccountDoesNotExistsException e) {
			System.out.println(e.getMessage());
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			//e.getStackTrace() logger
		}
		Renderer.waitForInput();
		
	}
	
	public static void viewAllActiveAccounts() {
		
		AccountDao accounDao = new AccountDao();
		ArrayList<Account> accounts = (ArrayList<Account>) accounDao.getAllActiveAccounts();
		
		try {
			
			if(accounts==null)
				throw new DatabaseConnectionFailedException();
			else if(accounts.size()==0)
				throw new ActiveAccountsNotAvailableException();
			
			for(Account account: accounts) {
				Renderer.renderAccount(account);
			}
			

		}catch (DatabaseConnectionFailedException e) {
			System.out.println(e.getMessage());
		}catch (ActiveAccountsNotAvailableException e) {
			System.out.println(e.getMessage());
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			//e.getStackTrace() logger
		}
		Renderer.waitForInput();
		
	}
	
	public static void viewAllInactiveAccounts() {
		
		AccountDao accounDao = new AccountDao();
		ArrayList<Account> accounts = (ArrayList<Account>) accounDao.getAllInactiveAccounts();
		
		try {
			
			if(accounts==null)
				throw new DatabaseConnectionFailedException();
			else if(accounts.size()==0)
				throw new ActiveAccountsNotAvailableException();
			
			for(Account account: accounts) {
				Renderer.renderAccount(account);
			}
			
			
			
		}catch (DatabaseConnectionFailedException e) {
			System.out.println(e.getMessage());
		}catch (ActiveAccountsNotAvailableException e) {
			System.out.println(e.getMessage());
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			//e.getStackTrace() logger
		}
		Renderer.waitForInput();
		
	}
	
}
