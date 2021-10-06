package com.revature.view;

public class Renderer {

	
	public static void renderScreen(String screen) {
		
		switch (screen) {
		case "Login Menu":
			clear();
			renderTitle(screen);
			renderLoginMenu();
			break;

		case "Main Menu":
			clear();
			renderTitle(screen);
			renderMainMenu();
			break;
		case "Employee Menu":
			clear();
			renderTitle(screen);
			renderEmployeeMenu();
			break;
		case "Admin Menu":
			clear();
			renderTitle(screen);
			renderAdminMenu();
			break;
		}
		
		
	}
	private static int renderLoginMenu() {
		System.out.println("\n1.Login");
		System.out.println("\n2.Apply");
		System.out.println("\n\n3.Login as employee");
		
		return 0;
		
	}
	private static int renderMainMenu() {
		System.out.println("\n1.View Accounts");
		System.out.println("\n2.Withdraw");
		System.out.println("\n3.Deposit");
		System.out.println("\n4.Transfer");
		System.out.println("\n\n5.Exit");
		 
		return 0;
	}
	private static int renderEmployeeMenu() {
		System.out.println("\n1.Approve accounts");
		System.out.println("\n2.Account details");
		System.out.println("\n\n3.Exit");
		
		return 0;
		
	}
	private static int renderAdminMenu() {
		System.out.println("\n1.Approve accounts");
		System.out.println("\n2.Account details");
		System.out.println("\n3.Withdraw");
		System.out.println("\n4.Deposit");
		System.out.println("\n5.Transfer");
		System.out.println("\n6.Cancel account");
		System.out.println("\n\n3.Exit");
		
		return 0;

	}
	
	private static void renderTitle(String title) {
		System.out.println("+------------------------------+");
		System.out.println("|                              |");
		System.out.println("|           RV's Bank          |");
		System.out.println("|                              |");
		System.out.println("+------------------------------+");
		System.out.println("\n"+title);
		System.out.println("--------------------------------------------------------------");
		System.out.println(" \nWhat do you want to do?");
	}
	
	private static void clear() {
		System.out.println("\u000C");
	}
}
