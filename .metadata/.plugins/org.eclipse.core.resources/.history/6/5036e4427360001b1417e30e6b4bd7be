package com.bookshop.shoppingcartms.persistence;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bookshop.shoppingcartms.persistence.model.ShoppingCart;

@Repository
public interface IShoppingCartRepository extends MongoRepository<ShoppingCart, ObjectId> {
	
	List<ShoppingCart> findByAccountId(String accountId);
	
	ShoppingCart findByAccountIdAndProductId(String accountId, String productId);
	
	void deleteByAccountId(String accountId);
	
}
