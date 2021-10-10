package com.revature.services;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.exceptions.AccountDoesNotExistsException;
import com.revature.exceptions.ActiveAccountsNotAvailableException;
import com.revature.exceptions.DatabaseConnectionFailedException;
import com.revature.model.Account;
import com.revature.view.Renderer;

public class EmployeeService {

	private static Scanner sc;
	
	public static void viewAccount() {
		
		AccountDao accounDao = new AccountDao();
		sc = new Scanner(System.in);
		try {
		
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
