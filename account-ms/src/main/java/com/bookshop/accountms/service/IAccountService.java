package com.bookshop.accountms.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.bookshop.accountms.persistence.model.Account;

public interface IAccountService {
	
	List<Account> getAll();
	
	Account getAccount(ObjectId id);
	
	Account createAccount(Account account);
	
	void deleteAccount(ObjectId id);
	
	Account updateAccount(Account account);
	
}
