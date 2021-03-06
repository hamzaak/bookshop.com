package com.bookshop.accountms.service.impl;

import java.util.List;
import java.util.UUID;

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
	public Account getAccount(String id) {
		return accountRepository.findById(id).get();
	}
	
	@Override
	public Account getAccountByName(String name) {
		return accountRepository.findByName(name);
	}

	@Override
	public Account createAccount(Account account) {
		account.setId(UUID.randomUUID().toString());
		return accountRepository.save(account);
	}

	@Override
	public void deleteAccount(String id) {
		accountRepository.deleteById(id);
	}

	@Override
	public Account updateAccount(Account account) {
		return accountRepository.save(account);
	}

}
