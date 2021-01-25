package com.bookshop.shoppingcartms.persistence.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "shoppingcart")
@Getter
@Setter
@NoArgsConstructor
public class ShoppingCart {

	@Id
	@Field(name = "shoppingcart_id")
	private ObjectId id;
	
	@Field(name = "account_id")
	private String accountId;
	
	@Field(name = "product_id")
	private String productId;
	
	@Field(name = "quantity")
	private BigInteger quantity;
	
	@Transient
	private String productTitle;
	
	@Transient
	private String productDescription;
	
	@Transient
	private BigDecimal productPrice;
	
	@Transient
	private BigInteger productQuantity;
	
}
