package com.revature.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.exceptions.AccountDoesNotExistsException;
import com.revature.exceptions.AccountNotActiveException;
import com.revature.exceptions.DatabaseConnectionFailedException;
import com.revature.exceptions.IncorrectMoneyFormatException;
import com.revature.exceptions.NotEnoughFoundsException;
import com.revature.model.Account;
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
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
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
				throw new AccountNotActiveException();
				
			for(Account account: accounts) {
				Renderer.renderAccount(account);
			}
		
		}catch(Exception e) {
			//logger
		}
		Renderer.waitForInput();

	}
	
	public static void WithdrawFromAccount() {
		
		AccountDao accountDao = new AccountDao();
		boolean success = false;
		sc = new Scanner(System.in);
		try {
		System.out.print("Account number: ");
		int idAccount = sc.nextInt();
		System.out.print("Money to withdraw: ");
		int moneyInt = formatMoney(sc.next());
			
		
		Account account = accountDao.getAccount(idAccount);
		if(account==null)
			throw new DatabaseConnectionFailedException();
		else if(account.getAccountNumber()==0)
			throw new AccountDoesNotExistsException();
		
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

			
		}catch(IncorrectMoneyFormatException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());//logger
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
