package com.revature.main;
import java.io.IOException;
import com.revature.controller.*;
import com.revature.controller.*;


public class Main {

    // Serve as an entry point for the application and house static variables for application wide access.
    public static void main(String[] args)
    {
    	switch (WindowsManager.loginWindow())
    	{
    	case 1:
    		WindowsManager.userLogin();
    	}
    	
        
    }


}
