package com.revature.view;

public class Renderer {
		
	public static int renderLoginMenu() {
		renderTitle("Login Menu");
		System.out.println("\n1.Login");
		System.out.println("\n2.Apply");
		System.out.println("\n\n3.Login as employee\n");
		
		return 0;
		
	}
	public static int renderMainMenu() {
		renderTitle("Main Menu");
		System.out.println("\n1.View Accounts");
		System.out.println("\n2.Withdraw");
		System.out.println("\n3.Deposit");
		System.out.println("\n4.Transfer");
		System.out.println("\n\n5.Exit\n");
		 
		return 0;
	}
	public static int renderEmployeeMenu() {
		renderTitle("Employee Menu");
		System.out.println("\n1.Approve accounts");
		System.out.println("\n2.Account details");
		System.out.println("\n\n3.Exit\n");
		
		return 0;
		
	}
	public static int renderAdminMenu() {
		renderTitle("Admin Menu");
		System.out.println("\n1.Approve accounts");
		System.out.println("\n2.Account details");
		System.out.println("\n3.Withdraw");
		System.out.println("\n4.Deposit");
		System.out.println("\n5.Transfer");
		System.out.println("\n6.Cancel account");
		System.out.println("\n\n3.Exit\n");
		
		return 0;

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
	
	public static void clear() {
		System.out.println("\u000C");
	}
}
