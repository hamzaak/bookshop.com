package com.bookshop.accountms.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bookshop.accountms.persistence.model.Account;

@Repository
public interface IAccountRepository extends MongoRepository<Account, String>{
	Account findByName(String name);
}
