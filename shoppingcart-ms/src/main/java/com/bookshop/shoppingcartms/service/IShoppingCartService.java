package com.bookshop.shoppingcartms.service;

import java.util.List;

import com.bookshop.shoppingcartms.persistence.model.ShoppingCartItem;

public interface IShoppingCartService {
	
	List<ShoppingCartItem> get(String accountId);
	
	List<ShoppingCartItem> add(ShoppingCartItem shoppingCartItem);
	
	List<ShoppingCartItem> remove(ShoppingCartItem shoppingCartItem);
	
	List<ShoppingCartItem> clear(String accountId);
	
}
