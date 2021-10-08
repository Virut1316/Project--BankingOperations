package com.revature.dao;

import java.util.List;

import com.revature.model.Account;
import com.revature.model.Customer;

public interface AccountDaoInterface {

	public List<Account> getAllAccounts();
	public List<Account> getAllAccounts(Customer customer);
	public List<Account> getAllActiveAccounts(Customer customer);
	public List<Account> getAllInativeAccounts(Customer customer);
}
