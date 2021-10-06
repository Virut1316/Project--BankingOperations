package com.revature.controller;

import com.revature.view.Renderer;
import java.util.Scanner;

public class WindowsManager {
	private static Scanner sc;
	
	public static int loginWindow() {
		//
		sc = new Scanner(System.in);
		Renderer.renderScreen("Login Menu");
		
		System.out.print("Your selection: ");
		return sc.nextInt();
		
	}
	
	public static boolean userLogin() {
		
		//
		sc = new Scanner(System.in);
		System.out.print("\nInput your username and password: ");
		System.out.print("\nUsername: ");
		sc.nextLine();
		System.out.print("Password: ");
		sc.nextLine();
		
		return true;
		
	}

}
