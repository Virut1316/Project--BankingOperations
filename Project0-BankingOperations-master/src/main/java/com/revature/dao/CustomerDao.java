package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.revature.daoUtils.ConnectionConfig;
import com.revature.daoUtils.Dao;
import com.revature.logger.LoggerManager;
import com.revature.model.Customer;

public class CustomerDao implements Dao<Customer> {

	ConnectionConfig connectionConfig = ConnectionConfig.getInstance();
	
	@Override
	public List<Customer> getAllElements() {
		//Not used in this version
		return null;
	}

	@Override
	public Customer getElementById(int id) {
		Customer customer = new Customer();
		
		try {
			Connection connection = connectionConfig.getConnection();
			
			String sql = "Select * from customers where customer_id = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				customer = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
			}
			connection.close();
			LoggerManager.logger.debug(preparedStatement.toString());
		}catch(Exception e){
			customer=null;
			LoggerManager.logger.error(e.getMessage());
		}
		
		return customer;
	}

	@Override
	public Customer getElementByUsername(String username) {
		
		Customer customer = null;
		
		try {
			Connection connection = connectionConfig.getConnection();
			username = username.trim();
			String sql = "Select * from customers where username = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				customer = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
			}
			
			if(customer==null)
				customer = new Customer();
			
			connection.close();
			LoggerManager.logger.debug(preparedStatement.toString());

		}catch(Exception e){
			customer=null;
			LoggerManager.logger.error(e.getMessage());
		}
		
		return customer;
	}

	@Override
	public boolean insertElement(Customer customer) {
		
		boolean success = true;
		try {
			Connection connection = connectionConfig.getConnection();
			String sql = "INSERT INTO public.customers(first_name, last_name, email, username, \"password\")"
					      + "VALUES(?,?,?,?,?);";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getLastName());
			preparedStatement.setString(3, customer.getEmail());
			preparedStatement.setString(4, customer.getUsername());
			preparedStatement.setString(5, customer.getPassword());
			
			preparedStatement.execute();
			
			connection.close();
			LoggerManager.logger.debug(preparedStatement.toString());

		} catch (Exception e) {
			LoggerManager.logger.error(e.getMessage());
			success = false;
		}
		
		return success;
	}

	@Override
	public boolean deleteElement(int id) {
		
		boolean success = true;
		try {
			Connection connection = connectionConfig.getConnection();
			String sql = "DELETE FROM customers "
					+ "WHERE customer_id = ?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();
			
			connection.close();
			LoggerManager.logger.debug(preparedStatement.toString());

		} catch (Exception e) {
			LoggerManager.logger.error(e.getMessage());
			success = false;
		}
		
		return success;

	}

	@Override
	public boolean updateElement(Customer element) {
		//Not used in this version
		return false;
	}
	

}
