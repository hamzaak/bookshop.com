package com.bookshop.shoppingcartms.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.shoppingcartms.api.IAccountServiceClient;
import com.bookshop.shoppingcartms.api.IProductServiceClient;
import com.bookshop.shoppingcartms.dto.AccountDTO;
import com.bookshop.shoppingcartms.dto.ProductDTO;
import com.bookshop.shoppingcartms.exception.CustomException;
import com.bookshop.shoppingcartms.persistence.IShoppingCartItemRepository;
import com.bookshop.shoppingcartms.persistence.model.ShoppingCartItem;
import com.bookshop.shoppingcartms.service.IShoppingCartService;

@Service
public class ShoppingCartService implements IShoppingCartService {

	@Autowired
	private IShoppingCartItemRepository shoppingCartItemRepository;
	
	@Autowired
	private IAccountServiceClient accountServiceClient;
	
	@Autowired
	private IProductServiceClient productServiceClient;
	
	@Override
	public List<ShoppingCartItem> get(String accountId) {
		return getCart(accountId);
	}
	
	@Override
	public List<ShoppingCartItem> add(ShoppingCartItem shoppingCartItem) {
		
		this.checkAccount(shoppingCartItem.getAccountId());
		
		ProductDTO product = this.getProduct(shoppingCartItem.getProductId());
		
		ShoppingCartItem originalShoppingCart = this.shoppingCartItemRepository.findByAccountIdAndProductId(
				shoppingCartItem.getAccountId(), shoppingCartItem.getProductId());
		if(originalShoppingCart != null) {
			originalShoppingCart.setQuantity(originalShoppingCart.getQuantity().add(shoppingCartItem.getQuantity()));
			
			if(product.getQuantity().compareTo(originalShoppingCart.getQuantity()) < 0) {
				throw new CustomException("Insufficient product!");
			}
			
			this.shoppingCartItemRepository.save(originalShoppingCart);
		} else {
			shoppingCartItem.setId(UUID.randomUUID().toString());
			
			if(product.getQuantity().compareTo(shoppingCartItem.getQuantity()) < 0) {
				throw new CustomException("Insufficient product!");
			}
			
			this.shoppingCartItemRepository.insert(shoppingCartItem);
		}
		
		return getCart(shoppingCartItem.getAccountId());
	}

	@Override
	public List<ShoppingCartItem> remove(ShoppingCartItem shoppingCartItem) {
		
		this.checkAccount(shoppingCartItem.getAccountId());
		this.checkProduct(shoppingCartItem.getProductId());
		
		ShoppingCartItem originalShoppingCart = this.shoppingCartItemRepository.findByAccountIdAndProductId(shoppingCartItem.getAccountId(), shoppingCartItem.getProductId());
		if(originalShoppingCart != null) {
			originalShoppingCart.setQuantity(originalShoppingCart.getQuantity().subtract(shoppingCartItem.getQuantity()));
			
			this.shoppingCartItemRepository.save(originalShoppingCart);
		}
		
		return getCart(shoppingCartItem.getAccountId());
	}

	@Override
	public List<ShoppingCartItem> clear(String accountId) {
		this.checkAccount(accountId);
		this.shoppingCartItemRepository.deleteByAccountId(accountId);
		return getCart(accountId);
	}
	
	private void checkAccount(String accountId) {
		AccountDTO account = this.accountServiceClient.getAccount(accountId);
		if(account == null) {
			throw new CustomException("Acount not found!");
		}
	}

	private void checkProduct(String productId) {
		ProductDTO product =  this.productServiceClient.getProduct(productId);
		if(product == null) {
			throw new CustomException("Product not found!");
		}
	}
	
	private ProductDTO getProduct(String productId) {
		ProductDTO product =  this.productServiceClient.getProduct(productId);
		if(product == null) {
			throw new CustomException("Product not found!");
		}
		
		return product;
	}
	
	private List<ShoppingCartItem> getCart(String accountId) {
		List<ShoppingCartItem> shoppingCartItems = this.shoppingCartItemRepository.findByAccountId(accountId);
		for(ShoppingCartItem cart: shoppingCartItems) {
			ProductDTO productDTO = this.productServiceClient.getProduct(cart.getProductId());
			cart.setProductTitle(productDTO.getTitle());
			cart.setProductDescription(productDTO.getDescription());
			cart.setProductPrice(productDTO.getPrice());
			cart.setProductQuantity(productDTO.getQuantity());
		}
		return shoppingCartItems;
	}

}
