package com.bookshop.accountms.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.accountms.persistence.IAccountRepository;
import com.bookshop.accountms.persistence.model.Account;
import com.bookshop.accountms.service.IAccountService;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private IAccountRepository accountRepository;
	
	@Override
	public List<Account> getAll() {
		return accountRepository.findAll();
	}

	@Override
	public Account getAccount(ObjectId id) {
		return accountRepository.findById(id).get();
	}

	@Override
	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public void deleteAccount(ObjectId id) {
		accountRepository.deleteById(id);
	}

	@Override
	public Account updateAccount(Account account) {
		return accountRepository.save(account);
	}

}
