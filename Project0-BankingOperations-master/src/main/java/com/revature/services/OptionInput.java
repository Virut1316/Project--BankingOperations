package com.revature.services;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.logger.LoggerManager;
import com.revature.view.Renderer;

public class OptionInput {
	private static Scanner sc;
	

	public static int choiceCatcher() { //Used to modularize the input in menus
		
		sc = new Scanner(System.in);
		int choice;
		try {			
			System.out.print("Your selection: ");
			choice = sc.nextInt();
		}catch (InputMismatchException e) {
			LoggerManager.logger.warn("User tried to input not valid data : "+e.getMessage());
			choice = 0;
		}catch (Exception e) {
			LoggerManager.logger.warn(e.getMessage());
			choice = 0;
		}

		return choice;
	
	}
}
