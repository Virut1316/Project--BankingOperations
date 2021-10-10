package com.revature.services;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.view.Renderer;

public class OptionInput {
	private static Scanner sc;
	

	public static int choiceCatcher() {
		
		sc = new Scanner(System.in);
		int choice;
		try {			
			System.out.print("Your selection: ");
			choice = sc.nextInt();
		}catch (InputMismatchException e) {
			choice = 0;
		}catch (Exception e) {
			//e.getStackTrace(); logger
			choice = 0;
		}

		return choice;
	
	}
}
