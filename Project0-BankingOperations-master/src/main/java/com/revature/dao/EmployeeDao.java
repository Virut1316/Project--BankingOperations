package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;

import java.sql.Connection;
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
			e.printStackTrace();
		}
		
		return employeeLs;
	}

	@Override
	public Employee getElementById(int id) {
		Employee employee = null;
		
		try {
		//We get the connection to de db
		Connection connection = connectionConfig.getConnection();
		//Create the Query
		String sql = "Select * from Employee where employee_id='"+id+"'";
		
		//Creating a statment from query
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		
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
		Employee employee = null;
		
		try {
		//We get the connection to de db
		Connection connection = connectionConfig.getConnection();
		//Create the Query
		String sql = "Select * from Employee where username='"+username+"'";
		
		//Creating a statment from query
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		
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
