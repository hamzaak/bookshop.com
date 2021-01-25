package com.bookshop.shoppingcartms.service;

import java.util.List;

import com.bookshop.shoppingcartms.persistence.model.ShoppingCart;

public interface IShoppingCartService {
	
	List<ShoppingCart> get(String accountId);
	
	List<ShoppingCart> add(ShoppingCart shoppingCart);
	
	List<ShoppingCart> remove(ShoppingCart shoppingCart);
	
	List<ShoppingCart> clear(String accountId);
	
}
