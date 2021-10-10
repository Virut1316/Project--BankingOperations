package com.revature.daoUtils;

import java.util.List;

import com.revature.model.Account;

public interface AccountDaoInterface {

	public List<Account> getAllAccounts();
	public List<Account> getAllAccounts(int idCustomer);
	public List<Account> getAllActiveAccounts(int idCustomer);
	public List<Account> getAllInactiveAccounts(int idCustomer);
	public boolean deleteAccount(int id);
	public boolean insertAccount(Account account,int idCustomer);
	public boolean updateAccount(Account account);
}
