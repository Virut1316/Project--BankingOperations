package com.revature.test.CustomerOperationsService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.*;

import com.revature.dao.AccountDao;
import com.revature.model.Account;
import com.revature.services.CustomerOperationsService;

public class CustomerServiceTest {

	
	@InjectMocks
	public CustomerOperationsService coService;
	
	@Mock
	public AccountDao accounDao;
	
	
	@Before
	public void iniMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void TestingViewAllActiveAccounts(){ //Checking if detects if an user is not active
		ArrayList<Account> accounts1 = new ArrayList<>();
		
		accounts1.add(new Account(1,false,20000));
		
		when(accounDao.getAllActiveAccounts(anyInt())).thenReturn(accounts1);
		
		//ArrayList<Account> accounts1 = new ArrayList<>();
		boolean test = coService.checkActive(0);
		
		assertEquals(test, false);
	}
	
}
