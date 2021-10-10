package com.revature.dao;

import com.revature.model.*;
import com.revature.view.Renderer;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.daoUtils.AccountDaoInterface;
import com.revature.daoUtils.ConnectionConfig;

public class AccountDao implements AccountDaoInterface{

	ConnectionConfig connectionConfig = ConnectionConfig.getInstance();
	
	@Override
	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<Account>();

		try {
			Connection connection = connectionConfig.getConnection();
			String sql = "Select * from account";
			
			
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			
			while(rs.next())
				accounts.add(new Account(rs.getInt(1),rs.getBoolean(3),rs.getInt(4)));
			
		} catch (Exception e) {
			// print in logger
			accounts = null;
		}
		
		return accounts;
	}

	@Override
	public List<Account> getAllAccounts(int idCustomer) {
		
		List<Account> accounts = new ArrayList<Account>();

		
		try {
			Connection connection = connectionConfig.getConnection();
			String sql = "Select * from account where customer_id=?";
			
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setInt(1, idCustomer);
			
			
			ResultSet rs = preparedstatement.executeQuery();			
			
			while(rs.next())
				accounts.add(new Account(rs.getInt(1),rs.getBoolean(3),rs.getInt(4)));
			
		} catch (Exception e) {
			// print in logger
			accounts = null;
		}
		
		return accounts;

	}

	@Override
	public List<Account> getAllActiveAccounts(int idCustomer) {
		List<Account> accounts = new ArrayList<Account>();

		try {
			Connection connection = connectionConfig.getConnection();
			String sql = "Select * from account where customer_id=? AND active=true";
			
			
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setInt(1, idCustomer);
			
			
			
			ResultSet rs = preparedstatement.executeQuery();			
			
			while(rs.next())
				accounts.add(new Account(rs.getInt(1),rs.getBoolean(3),rs.getInt(4)));
			
		} catch (Exception e) {
			System.out.println(e);// print in logger
			e.printStackTrace();
			accounts = null;
		}
		
		return accounts;
	}

	@Override
	public List<Account> getAllInactiveAccounts(int idCustomer) {
		List<Account> accounts = new ArrayList<Account>();
		
		try {
			Connection connection = connectionConfig.getConnection();
			String sql = "Select * from account where customer_id=? AND active=false";
			
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setInt(1, idCustomer);
			
			
			ResultSet rs = preparedstatement.executeQuery();			
			
			while(rs.next())
				accounts.add(new Account(rs.getInt(1),rs.getBoolean(3),rs.getInt(4)));
			
		} catch (Exception e) {
			// print in logger
			accounts = null;
		}
		
		return accounts;
	}

	@Override
	public boolean deleteAccount(int idCustomer) {
		boolean successfull = true;
		
		try {
			Connection connection = connectionConfig.getConnection();
			String sql = "DELETE FROM public.account WHERE account_id=?";
			
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setInt(1, idCustomer);
			
			preparedstatement.executeQuery();			
			
			
		} catch (Exception e) {
			// print in logger
			successfull = false;
		}
		
		return successfull;
	}

	@Override
	public boolean insertAccount(Account account,int idCustomer) {
		boolean successfull = true;
		
		try {
			Connection connection = connectionConfig.getConnection();
			String sql = "insert into account (customer_id,active,balance) values (?,?,?);";
			
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setInt(1, idCustomer);
			preparedstatement.setBoolean(2, account.isActive());
			preparedstatement.setInt(3, account.getBalance());
			
			preparedstatement.executeQuery();			
			
			
		} catch (Exception e) {
			// print in logger
			successfull = false;
		}
		return successfull;
	}

	@Override
	public boolean updateAccount(Account account) {
		boolean successfull = true;
		
		try {
			Connection connection = connectionConfig.getConnection();
			String sql = "UPDATE public.account SET active=?, balance=? WHERE account_id=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, account.isActive());
			preparedStatement.setInt(2, account.getBalance());
			preparedStatement.setInt(3, account.getAccountNumber());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();			
			
			
		} catch (Exception e) {
			e.printStackTrace();// print in logger
			successfull = false;
		}
		return successfull;
	}
	
	public Customer getOwner(int idAccount) {
		Customer customer = new Customer();

		try {
			Connection connection = connectionConfig.getConnection();
			String sql = "select * from account a left join customers c ON a.customer_id = c.customer_id where account_id = ?";
			
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idAccount);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while(rs.next())
				customer = new Customer(rs.getInt(2),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
			
		} catch (Exception e) {
			// print in logger
			customer = null;
		}
		return customer;
	}
	
	public Account getAccount(int idAccount) {
		Account account = null;
		
		try {
			Connection connection = connectionConfig.getConnection();
			String sql = "Select * from account where account_id=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idAccount);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
				account = new Account(rs.getInt(1), rs.getBoolean(3), rs.getInt(4));
			
			if(account==null)
				account = new Account();
			
		}catch(Exception e){
			//logger
			account = null;
		}
		
		return account;
	}
	
	public Account getAccountFromCustomer(int idAccount, int idCustomer) {
		Account account = null;
		
		try {
			Connection connection = connectionConfig.getConnection();
			String sql = "Select * from account where account_id=? AND customer_id=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idAccount);
			preparedStatement.setInt(2, idCustomer);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
				account = new Account(rs.getInt(1), rs.getBoolean(3), rs.getInt(4));
			
			if(account==null)
				account = new Account();
			
		}catch(Exception e){
			//logger
			account = null;
		}
		
		return account;
	}
	
	
	/*public boolean transfer(Account sender, Account receiver) {
		boolean success;
		try {
			Connection connection = connectionConfig.getConnection();
			connection.setAutoCommit(false); //Set this to disable commits to the database until called
			updateAccount(sender);
			Renderer.waitForInput();
			updateAccount(receiver);
			connection.commit(); // we call a commit so all the changes are "saved" on the DB
			connection.setAutoCommit(true); //we leave the autocommit in true to not affect other functionalities
			
			success = true;
			
		}catch(Exception e) {
			success = false;
		}
		
		
		return success;
	}*/
	
	public boolean transfer(Account sender, Account receiver) {
		boolean successfull = true;
		
		try {
			Connection connection = connectionConfig.getConnection();
			connection.setAutoCommit(false); //Disabling the autocommit mode to wait until callin it
			
			//FirstPart, sustract from sender
			String sql = "UPDATE public.account SET active=?, balance=? WHERE account_id=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, sender.isActive());
			preparedStatement.setInt(2, sender.getBalance());
			preparedStatement.setInt(3, sender.getAccountNumber());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();			
			Renderer.waitForInput();

			//Second parte adding to receiver
			
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
			preparedStatement2.setBoolean(1, receiver.isActive());
			preparedStatement2.setInt(2, receiver.getBalance());
			preparedStatement2.setInt(3, receiver.getAccountNumber());
			
			System.out.println(preparedStatement2);
			preparedStatement2.executeUpdate();	
			Renderer.waitForInput();

			connection.commit(); // Commit to apply changes
			connection.setAutoCommit(true); //going back to the default mode
			
		} catch (Exception e) {
			e.printStackTrace();// print in logger
			successfull = false;
		}
		return successfull;
}
	
}
