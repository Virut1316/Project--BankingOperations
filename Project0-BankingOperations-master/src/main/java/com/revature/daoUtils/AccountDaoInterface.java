package com.revature.daoUtils;

import java.util.List;

import com.revature.model.Account;
import com.revature.model.Customer;

public interface AccountDaoInterface {

	public List<Account> getAllAccounts();
	public List<Account> getAllAccounts(int idCustomer);
	public List<Account> getAllActiveAccounts();
	public List<Account> getAllInactiveAccounts();
	public List<Account> getAllActiveAccounts(int idCustomer);
	public List<Account> getAllInactiveAccounts(int idCustomer);
	public Customer getOwner(int idAccount);
	public Account getAccount(int idAccount);
	public Account getAccountFromCustomer(int idAccount, int idCustomer);
	public boolean deleteAccount(int id);
	public boolean insertAccount(Account account,int idCustomer);
	public boolean updateAccount(Account account);
	public boolean transfer(Account sender, Account receiver);
}
