package com.revature.main;
import java.io.IOException;

import com.revature.view.*;

public class Main {

    // Serve as an entry point for the application and house static variables for application wide access.
    public static void main(String[] args)
    {
    	
        Renderer.renderScreen("Main Menu");
        
        
        
        Renderer.renderScreen("Login Menu");
        Renderer.renderScreen("Employee Menu");
        Renderer.renderScreen("Admin Menu");
        
    }


}
