package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.revature.model.Customer;

public class CustomerDao implements Dao<Customer> {

	ConnectionConfig connectionConfig = ConnectionConfig.getInstance();
	
	@Override
	public List<Customer> getAllElements() {
		// TODO Auto-generated method stub
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
				customer = new Customer(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
			}
			
		}catch(Exception e){
			customer=null;
			System.out.println(e);
		}
		
		return customer;
	}

	@Override
	public Customer getElementByUsername(String username) {
		
		Customer customer = new Customer();
		
		try {
			Connection connection = connectionConfig.getConnection();
			
			String sql = "Select * from customers where username = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				customer = new Customer(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
			}
			
		}catch(Exception e){
			customer=null;
			System.out.println(e);
		}
		
		return customer;
	}

	@Override
	public void insertElement(Customer element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteElement(Customer element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateElement(Customer element) {
		// TODO Auto-generated method stub
		
	}

}
