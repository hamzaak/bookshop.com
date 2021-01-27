package com.bookshop.shoppingcartms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.shoppingcartms.persistence.model.ShoppingCart;
import com.bookshop.shoppingcartms.service.IShoppingCartService;

@RestController
@RequestMapping(value = "/api/shoppingcart")
public class ShoppingCartController {

	@Autowired
	private IShoppingCartService shoppingCartService;
	
	@GetMapping(value = "/get/{accountId}")
	public ResponseEntity<List<ShoppingCart>> add(@PathVariable("accountId") String accountId) {
		return ResponseEntity.ok(shoppingCartService.get(accountId));
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<List<ShoppingCart>> add(@RequestBody ShoppingCart shoppingCart) {
		return ResponseEntity.ok(shoppingCartService.add(shoppingCart));
	}
	
	@PostMapping(value = "/remove")
	public ResponseEntity<List<ShoppingCart>> remove(@RequestBody ShoppingCart shoppingCart) {
		return ResponseEntity.ok(shoppingCartService.remove(shoppingCart));
	}
	
	@DeleteMapping(value = "/clear/{accountId}")
	public ResponseEntity<List<ShoppingCart>> clear(@PathVariable("accountId") String accountId) {
		return ResponseEntity.ok(shoppingCartService.clear(accountId));
	}
}
