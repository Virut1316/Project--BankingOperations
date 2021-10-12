package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.daoUtils.ConnectionConfig;
import com.revature.daoUtils.Dao;
import com.revature.logger.LoggerManager;
import com.revature.model.Employee;

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
		//We get the connection to the db
		Connection connection = connectionConfig.getConnection();
		
		//Create the Query
		String sql = "Select * from Employee";
		
		//Creating a statment from query
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		
		while(rs.next()) {
			employeeLs.add(new Employee(rs.getInt(1),rs.getBoolean(2),rs.getString(3),rs.getString(4)));
		}
		connection.close();
		LoggerManager.logger.debug(sql);
		
		}
		catch (Exception e) {
			LoggerManager.logger.error(e.getMessage());
			employeeLs = null;
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
		String sql = "Select * from Employee where employee_id=?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		
		//Creating a statment from query
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			employee = new Employee(rs.getInt(1),rs.getBoolean(2),rs.getString(3),rs.getString(4));
		}
			
		if(employee==null) //if it does not retrieve anything then an empty employee is returned
			employee = new Employee();
		connection.close();
		LoggerManager.logger.debug(preparedStatement.toString());

		}
		catch (Exception e) {
			LoggerManager.logger.error(e.getMessage());
			employee = null; //if an exception takes place during execution then a null employee is returned
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
		String sql = "Select * from Employee where username=?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, username);
			
		//Creating a statment from query
		ResultSet rs = preparedStatement.executeQuery();

		
		while(rs.next()) {
			employee = new Employee(rs.getInt(1),rs.getBoolean(2),rs.getString(3),rs.getString(4));
		}
			
		if(employee==null) //if it does not retrieve anything then an empty employee is returned
			employee = new Employee();
		
		connection.close();
		LoggerManager.logger.debug(preparedStatement.toString());

		}
		catch(Exception e) {
			LoggerManager.logger.error(e.getMessage());
			employee = null;
		}

		
		
		return employee;
	}
	
	@Override
	public boolean insertElement(Employee element) {
		// Not used in this version
		return false;
	}

	@Override
	public boolean deleteElement(int id) {
		
		// Not used in this version
		return false;
	}

	@Override
	public boolean updateElement(Employee element) {
		// Not used in this version
		return false;
	}


	
	
}
