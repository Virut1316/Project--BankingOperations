package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.DatabaseConnectionFailedException;
import com.revature.model.Employee;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeDao implements Dao<Employee>{

	ConnectionConfig connectionConfig = ConnectionConfig.getInstance();
	
	@Override
	public List<Employee> getAllElements() {
		
		List<Employee> employeeLs = new ArrayList<Employee>(); 
		
		try {
		//We get the connection to de db
		Connection connection = connectionConfig.getConnection();
		
		//Create the Query
		String sql = "Select * from Employee";
		
		//Creating a statment from query
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		
		while(rs.next()) {
			employeeLs.add(new Employee(rs.getBoolean(2),rs.getString(3),rs.getString(4)));
		}
		}
		catch (Exception e) {
			//e.printStackTrace(); we need to print this in the logger
		}
		
		return employeeLs;
	}

	@Override
	public Employee getElementById(int id) {
		Employee employee = new Employee();
		
		try {
		//We get the connection to de db
		Connection connection = connectionConfig.getConnection();
		//Create the Query
		String sql = "Select * from Employee where employee_id=?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		
		//Creating a statment from query
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			employee = new Employee();
			employee.setAdmin(rs.getBoolean(2));
			employee.setUsername(rs.getString(3));
			employee.setPassword(rs.getString(4));
		}
			
		
		}
		catch (Exception e) {
			e.printStackTrace();
			employee = null;
		}
		
		return employee;
	}

	@Override
	public Employee getElementByUsername(String username) {
		Employee employee = new Employee();
		
		try {
		//We get the connection to de db
		Connection connection = connectionConfig.getConnection();
		//Create the Query
		String sql = "Select * from Employee where username=?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, username);
			
		//Creating a statment from query
		ResultSet rs = preparedStatement.executeQuery();

		
		while(rs.next()) {
			employee = new Employee();
			employee.setAdmin(rs.getBoolean(2));
			employee.setUsername(rs.getString(3));
			employee.setPassword(rs.getString(4));
		}
			
		
		}
		catch(Exception e) {
			e.printStackTrace(); //Need to send this to the loger
			employee = null;
		}

		
		
		return employee;
	}
	
	@Override
	public void insertElement(Employee element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteElement(Employee element) {
		//Not necesary to implement at this project
		
	}

	@Override
	public void updateElement(Employee element) {
		//Not necesary to implement at this project
		
	}


	
	
}
