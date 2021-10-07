package com.revature.main;
import java.io.IOException;
import com.revature.controller.*;
import com.revature.model.Employee;
import com.revature.view.Renderer;
import com.revature.controller.*;


public class Main {

    // Serve as an entry point for the application and house static variables for application wide access.
    public static void main(String[] args)
    {
    	Employee employee=null;
    	switch (WindowsManager.loginWindow())
    	{
    	case 3:
    		employee = WindowsManager.EmployeeLogin();
    		System.out.print(employee);
    		break;
    	}
    	
    	if(employee!=null)
    		Renderer.renderScreen("Employee Menu");
    	
        
    }


}
