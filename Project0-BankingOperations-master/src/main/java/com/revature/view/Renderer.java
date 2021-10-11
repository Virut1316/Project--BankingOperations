package com.revature.view;

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
		System.out.println("\n1.Approve accounts");
		System.out.println("\n2.Account details");
		System.out.println("\n3.View all active accounts");
		System.out.println("\n4.View all inactive accounts");
		System.out.println("\n\n5.Exit\n");
		
		
	}
	public static void renderAdminMenu() {
		renderTitle("Admin Menu");
		System.out.println("\n1.Approve accounts");
		System.out.println("\n2.Account details");
		System.out.println("\n3.View all active accounts");
		System.out.println("\n4.View all inactive accounts");
		System.out.println("\n5.Withdraw");
		System.out.println("\n6.Deposit");
		System.out.println("\n7.Transfer");
		System.out.println("\n8.Cancel account");
		System.out.println("\n\n9.Exit\n");

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
		System.out.println("Owner: "+owner.getFirstName()+" "+owner.getLastName());
		System.out.println("Balance: "+((float)account.getBalance()/(float)100)+"$");
		System.out.println("Status: "+(account.isActive()?"Active":"Pending"));
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
