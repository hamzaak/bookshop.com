package com.bookshop.shoppingcartms.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bookshop.shoppingcartms.persistence.model.ShoppingCartItem;

@Repository
public interface IShoppingCartItemRepository extends MongoRepository<ShoppingCartItem, String> {
	
	List<ShoppingCartItem> findByAccountId(String accountId);
	
	ShoppingCartItem findByAccountIdAndProductId(String accountId, String productId);
	
	void deleteByAccountId(String accountId);
	
}
