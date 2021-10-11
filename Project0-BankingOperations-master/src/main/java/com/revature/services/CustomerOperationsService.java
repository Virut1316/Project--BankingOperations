package com.revature.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.exceptions.AccountAlreadyActiveException;
import com.revature.exceptions.AccountAlreadyInProgress;
import com.revature.exceptions.AccountDoesNotExistsException;
import com.revature.exceptions.AccountNotActiveException;
import com.revature.exceptions.ActiveAccountsNotAvailableException;
import com.revature.exceptions.DatabaseConnectionFailedException;
import com.revature.exceptions.IncorrectMoneyFormatException;
import com.revature.exceptions.NotEnoughFoundsException;
import com.revature.exceptions.TargetAccountNotAvailableException;
import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.view.Renderer;

public class CustomerOperationsService {
	private static Scanner sc;
	
	public static boolean checkActive(int idCustomer) {
		AccountDao accounDao = new AccountDao();
		boolean active = true;
		
		try {
			ArrayList<Account> activeAccounts = (ArrayList<Account>) accounDao.getAllActiveAccounts(idCustomer);
			
			if(activeAccounts==null)
				throw new DatabaseConnectionFailedException();
			else if(activeAccounts.size()==0)
				throw new AccountNotActiveException();
			
		}catch (DatabaseConnectionFailedException e) {
			System.out.println(e.getMessage());
			active = false;
			Renderer.waitForInput();
		}
		catch (AccountNotActiveException e) {
			System.out.println(e.getMessage());
			active = false;
			Renderer.waitForInput();
		}
		catch (Exception e) {
			// System.out.println(e.getMessage()); logger
			active = false;
			Renderer.waitForInput();
		}
		
		return active;
	}
	
	public static void ViewAccounts(int idCustomer) {
		
		AccountDao accountDao = new AccountDao();
		
		ArrayList<Account> accounts = (ArrayList<Account>) accountDao.getAllAccounts(idCustomer);
		
		
		try {
			
			if(accounts == null)
				throw new DatabaseConnectionFailedException();
			else if (accounts.size()==0)
				throw new ActiveAccountsNotAvailableException();
				
			for(Account account: accounts) {
				Renderer.renderAccount(account);
			}
		
		}catch(DatabaseConnectionFailedException e) {
			System.out.println(e.getMessage());
		}
		catch(ActiveAccountsNotAvailableException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			//logger
		}
		Renderer.waitForInput();

	}
	
	public static void withdrawFromAccount(int idCustomer) {
		
		AccountDao accountDao = new AccountDao();
		boolean success = false;
		sc = new Scanner(System.in);
		try {
		System.out.print("Account number: ");
		int idAccount = sc.nextInt();
		System.out.print("Amount to withdraw: ");
		int moneyInt = formatMoney(sc.next());
			
		
		Account account = accountDao.getAccountFromCustomer(idAccount,idCustomer);

		if(account==null)
			throw new DatabaseConnectionFailedException();
		else if(account.getAccountNumber()==0)
			throw new AccountDoesNotExistsException();
		else if (!account.isActive())
			throw new TargetAccountNotAvailableException();

		if((account.getBalance()-moneyInt)>=0) {
			success = accountDao.updateAccount(new Account(idAccount, account.isActive(), account.getBalance()-moneyInt));
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
	
	public static void transferToAccount(int idCustomer) {
		
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
			

		Account senderAccount = accountDao.getAccountFromCustomer(idSenderAccount,idCustomer);
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
	
public static void DepositToAccount(int idCustomer) {
		
		AccountDao accountDao = new AccountDao();
		boolean success = false;
		sc = new Scanner(System.in);
		try {
		System.out.print("Account number: ");
		int idAccount = sc.nextInt();
		System.out.print("Money to deposit: ");
		int moneyInt = formatMoney(sc.next());
			
		
		Account account = accountDao.getAccountFromCustomer(idAccount,idCustomer);
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
	
	public static void ApplyForAccount(int idCustomer) {
		
		AccountDao accountDao = new AccountDao();
		sc = new Scanner(System.in);
		String choice; 
		boolean success =false;
		try {
			ArrayList<Account> inactiveAccounts = (ArrayList<Account>) accountDao.getAllInactiveAccounts(idCustomer);
			if(inactiveAccounts.size()==1) {
				System.out.println("\nAccount already in progress");
				Renderer.renderAccount(inactiveAccounts.get(0));
				throw new AccountAlreadyInProgress();
			}

			
			System.out.println("You want to apply to open a new Account? You'll have to wait until an employee approves it for you");
			System.out.print("Proceed? (Y/n): ");
			choice = sc.next();
			
			if(choice.equals("Y")||choice.equals("y"))
				success = accountDao.insertAccount(new Account(0,false,0), idCustomer);
			else
				System.out.println("Operation cancelled");
			
			success=true;
			
		}catch(DatabaseConnectionFailedException e) {
			System.out.println(e.getMessage());
		}
		catch(AccountAlreadyActiveException e) {
			System.out.println(e.getMessage());
		}
		catch(AccountDoesNotExistsException e) {
			System.out.println(e.getMessage());
		}
		catch(AccountAlreadyInProgress e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			
		}
		
		if(success)
			System.out.println("Application for new account successful");		
		else
			System.out.println("Application for new account failed");
		
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
