package com.bookshop.accountms.service;

import java.util.List;

import com.bookshop.accountms.persistence.model.Account;

public interface IAccountService {
	
	List<Account> getAll();
	
	Account getAccount(String id);
	
	Account getAccountByName(String name);
	
	Account createAccount(Account account);
	
	void deleteAccount(String id);
	
	Account updateAccount(Account account);
	
}
