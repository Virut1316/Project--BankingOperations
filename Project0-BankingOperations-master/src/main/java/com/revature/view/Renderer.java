package com.revature.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.model.Account;
import com.revature.model.Customer;

public class Renderer {
	
	protected final static int accountNumberLength = 5;
	private static Scanner sc;
	
	public static void renderLoginMenu() {
		renderTitle("Login Menu");
		System.out.println("\n1.Login");
		System.out.println("\n2.Apply");
		System.out.println("\n\n3.Login as employee\n");
		System.out.println("\n4.Exit\n");

		
	}
	public static void renderMainMenu() {
		renderTitle("Main Menu");
		System.out.println("\n1.View Accounts");
		System.out.println("\n2.Withdraw");
		System.out.println("\n3.Deposit");
		System.out.println("\n4.Transfer");
		System.out.println("\n5.Apply for new account");
		System.out.println("\n\n6.Exit\n");
		 
	}
	public static void renderEmployeeMenu() {
		renderTitle("Employee Menu");
		System.out.println("\n1.Approve/Deny accounts");
		System.out.println("\n2.Account details");
		System.out.println("\n3.Customer details");
		System.out.println("\n4.View all active accounts");
		System.out.println("\n5.View all inactive accounts");
		System.out.println("\n\n6.Exit\n");
		
		
	}
	public static void renderAdminMenu() {
		renderTitle("Admin Menu");
		System.out.println("\n1.Approve/Deny accounts");
		System.out.println("\n2.Account details");
		System.out.println("\n3.Customer details");
		System.out.println("\n4.View all active accounts");
		System.out.println("\n5.View all inactive accounts");
		System.out.println("\n6.Withdraw");
		System.out.println("\n7.Deposit");
		System.out.println("\n8.Transfer");
		System.out.println("\n9.Cancel account");
		System.out.println("\n\n10.Exit\n");

	}
	
	public static void renderTitle(String title) {
		System.out.println("+------------------------------+");
		System.out.println("|                              |");
		System.out.println("|           RV's Bank          |");
		System.out.println("|                              |");
		System.out.println("+------------------------------+");
		System.out.println("\n"+title);
		System.out.println("--------------------------------------------------------------");
		System.out.println(" \nWhat do you want to do?");
	}
	
	public static void renderAccount(Account account) {
		AccountDao accountDao = new AccountDao();
		Customer owner = accountDao.getOwner(account.getAccountNumber());
		
		
		System.out.println("+------------------------------+");
		System.out.println("Account Number: "+fillAccountNumber(account.getAccountNumber()));
		System.out.println("Owner: "+owner.getFirstName()+" "+owner.getLastName()+" ("+owner.getUsername()+")");
		System.out.println("Balance: "+((float)account.getBalance()/(float)100)+"$");
		System.out.println("Status: "+(account.isActive()?"Active":"Pending"));
		System.out.println("+------------------------------+");
	}
	
	public static void renderCustomer(Customer customer) {
		AccountDao accountDao = new AccountDao();
		int total=0;
		ArrayList<Account> accounts = (ArrayList<Account>) accountDao.getAllAccounts(customer.getId());
		
		System.out.println("+------------------------------+");
		System.out.println("Name: "+customer.getFirstName()+" "+customer.getLastName());
		System.out.println("Username: "+customer.getUsername());
		System.out.println("email: "+customer.getEmail());

		System.out.println("Accounts:");
		for(Account account : accounts) {
			total +=account.getBalance();
			System.out.println("> Account #"+account.getAccountNumber()+"  Balance: "+((float)account.getBalance()/(float)100)+"$");
		}
		System.out.println("Total: "+((float)total/(float)100)+"$");
		System.out.println("+------------------------------+");
		
	}
	
	private static String fillAccountNumber(int number) {
		StringBuilder sB = new StringBuilder();
		String numberString = ""+number;
		for(int i=0;(i+numberString.length())<accountNumberLength;i++) {
			sB.append(0);
		}
		sB.append(numberString);
		return sB.toString();
	}
	
	public static void clear() {
		System.out.println("\u000C");
	}
	
	public static void waitForInput() {
		sc = new Scanner(System.in);
		System.out.print("Press any key to continue . . . ");
	    sc.nextLine();
	}

}
