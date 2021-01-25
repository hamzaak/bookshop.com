package com.bookshop.accountms.api;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookshop.accountms.persistence.model.Account;
import com.bookshop.accountms.service.IAccountService;

@RestController
@RequestMapping(value = "/api/accounts")
public class AccountController {

	@Autowired
	private IAccountService accountService;
	
	@GetMapping(value = "/get/all")
	public ResponseEntity<List<Account>> getAll() {
		return ResponseEntity.ok(accountService.getAll());
	}
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<Account> getAccount(@PathVariable("id") ObjectId id) {
		return ResponseEntity.ok(accountService.getAccount(id));
	}
	
	@PutMapping(value = "/create")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		return ResponseEntity.ok(accountService.createAccount(account));
	}
	
	@PostMapping(value = "/update")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
		return ResponseEntity.ok(accountService.updateAccount(account));
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable("id") ObjectId id) {
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account has been deleted successfully");
	}
	
}
