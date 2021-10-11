package com.revature.services;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.CustomerDao;
import com.revature.exceptions.AccountAlreadyActiveException;
import com.revature.exceptions.AccountDoesNotExistsException;
import com.revature.exceptions.ActiveAccountsNotAvailableException;
import com.revature.exceptions.DatabaseConnectionFailedException;
import com.revature.exceptions.IncorrectMoneyFormatException;
import com.revature.exceptions.NotEnoughFoundsException;
import com.revature.exceptions.TargetAccountNotAvailableException;
import com.revature.model.Account;
import com.revature.model.Customer;
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
			e.printStackTrace();
			System.out.println(e.getMessage());
			//e.getStackTrace() logger
		}
		Renderer.waitForInput();
		
	}
	
	public static void adminWithdraw() {
		
		AccountDao accountDao = new AccountDao();
		sc = new Scanner(System.in);
		boolean success = false;
		try {
		
		System.out.print("Account number to withdraw from: ");
		int accountId = sc.nextInt();
			
		Account account = accountDao.getAccount(accountId);
		
		if(account==null)
			throw new DatabaseConnectionFailedException();
		else if(account.getAccountNumber()==0)
			throw new AccountDoesNotExistsException();
		else if (!account.isActive())
			throw new TargetAccountNotAvailableException();
				
		System.out.print("Amount to withdraw: ");
		int moneyInt = formatMoney(sc.next());
		
		if((account.getBalance()-moneyInt)>=0) {
			success = accountDao.updateAccount(new Account(account.getAccountNumber(), account.isActive(), account.getBalance()-moneyInt));
		}
		else
			throw new NotEnoughFoundsException();
			
		if(success) {
			System.out.println("Operation successful");
		}
		else
			System.out.println("Operation failed, please try again later");

			
		}catch(DatabaseConnectionFailedException e) {
			System.out.println(e.getMessage());
		}catch(AccountDoesNotExistsException e) {
			System.out.println(e.getMessage());
		}catch(TargetAccountNotAvailableException e) {
			System.out.println(e.getMessage());
		}catch(IncorrectMoneyFormatException e) {
			System.out.println(e.getMessage());
		}
		catch(NotEnoughFoundsException e) {
			System.out.println(e.getMessage());
		}
		catch(InputMismatchException e) {
			System.out.println("Input data does not correspond to fields");
		}
		catch(Exception e) {
			//System.out.println(e.getMessage());//logger
		}
		
		Renderer.waitForInput();
		
	}
	public static void adminDeposit() {
		AccountDao accountDao = new AccountDao();
		boolean success = false;
		sc = new Scanner(System.in);
		try {
		System.out.print("Account number: ");
		int idAccount = sc.nextInt();
		System.out.print("Money to deposit: ");
		int moneyInt = formatMoney(sc.next());
			
		
		Account account = accountDao.getAccount(idAccount);
		if(account==null)
			throw new DatabaseConnectionFailedException();
		else if(account.getAccountNumber()==0)
			throw new AccountDoesNotExistsException();
		else if (!account.isActive())
			throw new TargetAccountNotAvailableException();
		
			success = accountDao.updateAccount(new Account(idAccount, account.isActive(), account.getBalance()+moneyInt));
			
		if(success) {
			System.out.println("Operation successful");
		}
		else
			System.out.println("Operation failed, please try again later");

			
		}catch(DatabaseConnectionFailedException e) {
			System.out.println(e.getMessage());
		}catch(AccountDoesNotExistsException e) {
			System.out.println(e.getMessage());
		}catch(TargetAccountNotAvailableException e) {
			System.out.println(e.getMessage());
		}catch(IncorrectMoneyFormatException e) {
			System.out.println(e.getMessage());
		}
		catch(NotEnoughFoundsException e) {
			System.out.println(e.getMessage());
		}
		catch(InputMismatchException e) {
			System.out.println("Input data does not correspond to fields");
		}
		catch(Exception e) {
			//System.out.println(e.getMessage());//logger
		}
		
		Renderer.waitForInput();
	}
	public static void adminTransfer() {
		
		AccountDao accountDao = new AccountDao();
		boolean success = false;
		sc = new Scanner(System.in);
		try {
		System.out.print("Sender account number: ");
		int idSenderAccount = sc.nextInt();
		System.out.print("Money to send: ");
		int moneyInt = formatMoney(sc.next());
		System.out.print("Receiver account number: ");
		int idReceiverAccount = sc.nextInt();
		
		if(idSenderAccount==idReceiverAccount) {
			System.out.println("You cant send money to the same account");
			Renderer.waitForInput();
			return;
		}
			

		Account senderAccount = accountDao.getAccount(idSenderAccount);
		if(senderAccount==null)
			throw new DatabaseConnectionFailedException();
		else if(senderAccount.getAccountNumber()==0)
			throw new AccountDoesNotExistsException();
		else if (!senderAccount.isActive())
			throw new TargetAccountNotAvailableException();
		
		Account receiverAccount = accountDao.getAccount(idReceiverAccount);
		if(receiverAccount==null)
			throw new DatabaseConnectionFailedException();
		else if(receiverAccount.getAccountNumber()==0)
			throw new AccountDoesNotExistsException();
		else if (!receiverAccount.isActive())
			throw new TargetAccountNotAvailableException();

		
	    // Example in https://docs.oracle.com/javase/tutorial/jdbc/basics/transactions.html for transaction in Java
		if((senderAccount.getBalance()-moneyInt)>=0) {		
			senderAccount.setBalance(senderAccount.getBalance()-moneyInt);
			receiverAccount.setBalance(receiverAccount.getBalance()+moneyInt);
			success = accountDao.transfer(senderAccount, receiverAccount);
		}
		else
			throw new NotEnoughFoundsException();
			
		if(success) {
			System.out.println("Operation successful");
		}
		else
			System.out.println("Operation failed, please try again later");

			
		}catch(DatabaseConnectionFailedException e) {
			System.out.println(e.getMessage());
		}catch(AccountDoesNotExistsException e) {
			System.out.println(e.getMessage());
		}catch(TargetAccountNotAvailableException e) {
			System.out.println(e.getMessage());
		}catch(IncorrectMoneyFormatException e) {
			System.out.println(e.getMessage());
		}
		catch(NotEnoughFoundsException e) {
			System.out.println(e.getMessage());
		}
		catch(InputMismatchException e) {
			System.out.println("Input data does not correspond to fields");
		}
		catch(Exception e) {
			//System.out.println(e.getMessage());//logger
		}
		
		Renderer.waitForInput();
		
	}
	public static void adminCancel() {
		AccountDao accountDao = new AccountDao();
		CustomerDao customerDao = new CustomerDao();
		sc = new Scanner(System.in);
		boolean success = false;
		String choice;
		boolean accountDeleter;
		
		Customer owner;
		try {
		
		System.out.print("Account number to cancel: ");
		int accountId = sc.nextInt();
			
		Account account = accountDao.getAccount(accountId);
		
		if(account==null)
			throw new DatabaseConnectionFailedException();
		else if(account.getAccountNumber()==0)
			throw new AccountDoesNotExistsException();

				
		
		System.out.println("This account is going to be canceled/deleted ");
		Renderer.renderAccount(account);
		owner =accountDao.getOwner(accountId);
		accountDeleter = (accountDao.getAllAccounts(owner.getId()).size())<=1;//if it is the last account of the customer the account gets deleted too
		if(accountDeleter)
		System.out.println("Warning: This account is going to delete the user with it");
		System.out.print("Proceed? (Y/n): ");
		choice = sc.next();
		
		if(choice.equals("Y")||choice.equals("y"))
		{	
			if(accountDeleter) {
				success = accountDao.deleteAccount(accountId);	
				success = customerDao.deleteElement(owner.getId());
			}
			else {
				success = accountDao.deleteAccount(accountId);	

			}

		}
		else
		{
			System.out.println("Operation cancelled");
			success = true;
		}
			
		if(success) {
			System.out.println("Operation successful");
		}
		else
			System.out.println("Operation failed, please try again later");

			
		}catch(DatabaseConnectionFailedException e) {
			System.out.println(e.getMessage());
		}catch(AccountDoesNotExistsException e) {
			System.out.println(e.getMessage());
		}catch(TargetAccountNotAvailableException e) {
			System.out.println(e.getMessage());
		}catch(InputMismatchException e) {
			System.out.println("Input data does not correspond to fields");
		}
		catch(Exception e) {
			//System.out.println(e.getMessage());//logger
		}
		
		Renderer.waitForInput();
	}
	
	private static int formatMoney(String money) throws IncorrectMoneyFormatException{
		int moneyInt=0;
		try {
			
		if(money.contains(",")||!money.contains(".")||(money.indexOf(".")!=money.length()-3))
			throw new IncorrectMoneyFormatException();
		
		moneyInt = (int)(Float.parseFloat(money)*100);
			
		}catch (IncorrectMoneyFormatException e) {
			moneyInt = -1;
			throw new IncorrectMoneyFormatException();
			
		}
		catch (Exception e) {
			//e.printStackTrace();
			//System.out.println(e.getMessage());	//logger
			moneyInt = -1;
			throw new IncorrectMoneyFormatException();
			
		}
		
		return moneyInt;
	}
	
}
