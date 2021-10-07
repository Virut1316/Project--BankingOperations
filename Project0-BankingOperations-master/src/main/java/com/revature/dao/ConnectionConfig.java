package com.revature.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionConfig {
	
	private static ConnectionConfig connection;
	private static Properties properties = new Properties();
	
	private ConnectionConfig() {
		//The new instance can only be created from inside, because is a Singleton and it happens once
	}
	
	//We declare syncronized to avoid problems when accessing to the same object, then we test if the connections is already instanciated, if not we do it ourselves
	public static synchronized ConnectionConfig getInstance() {
		if(connection == null)
		{
			connection = new ConnectionConfig();
			return connection;
		}
		else 
			return connection; //The instance exists so we return it
		
	}
	
	public Connection getConnection() {
		
		//We create a inputStream and take the .properties to read the parameters
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream InputStream = classLoader.getResourceAsStream("jdbc.properties");
		String url= "",port= "",password= "",username = "",database = "";
		
		try {
			//We read the .properties and extract the data
			properties.load(InputStream);
			url = (String)properties.getProperty("url");
			port = (String)properties.getProperty("port");
			database = properties.getProperty("database");
			username = (String)properties.getProperty("username");
			password = (String)properties.getProperty("password");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Connection connection;
		
		try {
			connection = DriverManager.getConnection(url+":"+port+"/"+database, username, password); //We get the actual connection and return ir
			return connection;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null; // if we are not able to stablish a connection with the db we return null
		
	}
	
	
}
