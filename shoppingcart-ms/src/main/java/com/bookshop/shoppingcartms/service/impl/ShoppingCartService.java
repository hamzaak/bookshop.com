package com.bookshop.shoppingcartms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.shoppingcartms.api.IAccountServiceClient;
import com.bookshop.shoppingcartms.api.IProductServiceClient;
import com.bookshop.shoppingcartms.dto.AccountDTO;
import com.bookshop.shoppingcartms.dto.ProductDTO;
import com.bookshop.shoppingcartms.exception.CustomException;
import com.bookshop.shoppingcartms.persistence.IShoppingCartRepository;
import com.bookshop.shoppingcartms.persistence.model.ShoppingCart;
import com.bookshop.shoppingcartms.service.IShoppingCartService;

@Service
public class ShoppingCartService implements IShoppingCartService {

	@Autowired
	private IShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private IAccountServiceClient accountServiceClient;
	
	@Autowired
	private IProductServiceClient productServiceClient;
	
	@Override
	public List<ShoppingCart> get(String accountId) {
		return getCart(accountId);
	}
	
	@Override
	public List<ShoppingCart> add(ShoppingCart shoppingCart) {
		AccountDTO account = this.getAccount(shoppingCart.getAccountId());
		ProductDTO product = this.getProduct(shoppingCart.getProductId());
		
		ShoppingCart originalShoppingCart = shoppingCartRepository.findByAccountIdAndProductId(shoppingCart.getAccountId(), shoppingCart.getProductId());
		if(originalShoppingCart != null) {
			originalShoppingCart.setQuantity(originalShoppingCart.getQuantity().add(shoppingCart.getQuantity()));
			
			if(product.getQuantity().compareTo(originalShoppingCart.getQuantity()) < 0) {
				throw new CustomException("Insufficient product!");
			}
			
			shoppingCartRepository.save(originalShoppingCart);
		} else {
			
			if(product.getQuantity().compareTo(shoppingCart.getQuantity()) < 0) {
				throw new CustomException("Insufficient product!");
			}
			
			this.shoppingCartRepository.insert(shoppingCart);
		}
		
		return getCart(shoppingCart.getAccountId());
	}

	@Override
	public List<ShoppingCart> remove(ShoppingCart shoppingCart) {
		
		AccountDTO account = this.getAccount(shoppingCart.getAccountId());
		ProductDTO product = this.getProduct(shoppingCart.getProductId());
		
		ShoppingCart originalShoppingCart = this.shoppingCartRepository.findByAccountIdAndProductId(shoppingCart.getAccountId(), shoppingCart.getProductId());
		if(originalShoppingCart != null) {
			originalShoppingCart.setQuantity(originalShoppingCart.getQuantity().subtract(shoppingCart.getQuantity()));
			
			this.shoppingCartRepository.save(originalShoppingCart);
		}
		
		return getCart(shoppingCart.getAccountId());
	}

	@Override
	public List<ShoppingCart> clear(String accountId) {
		AccountDTO account = this.getAccount(accountId);
		this.shoppingCartRepository.deleteByAccountId(accountId);
		return getCart(accountId);
	}
	
	private AccountDTO getAccount(String accountId) {
		AccountDTO account = this.accountServiceClient.getAccount(accountId);
		if(account == null) {
			throw new CustomException("Acount not found!");
		}
		
		return account;
	}
	
	private ProductDTO getProduct(String productId) {
		ProductDTO product =  this.productServiceClient.getProduct(productId);
		if(product == null) {
			throw new CustomException("Product not found!");
		}
		
		return product;
	}
	
	private List<ShoppingCart> getCart(String accountId) {
		List<ShoppingCart> shoppingCartItems = this.shoppingCartRepository.findByAccountId(accountId);
		for(ShoppingCart cart: shoppingCartItems) {
			ProductDTO productDTO = this.productServiceClient.getProduct(cart.getProductId());
			cart.setProductTitle(productDTO.getTitle());
			cart.setProductDescription(productDTO.getDescription());
			cart.setProductPrice(productDTO.getPrice());
			cart.setProductQuantity(productDTO.getQuantity());
		}
		return shoppingCartItems;
	}

}