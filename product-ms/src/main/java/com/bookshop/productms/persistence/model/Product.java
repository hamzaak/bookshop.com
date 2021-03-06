package com.bookshop.productms.persistence.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "products")
@Builder
@Getter
@Setter
public class Product {
	@Id
	@Field(name = "id")
	private String id;
	
	@Field(name = "title")
	private String title;
	
	@Field(name = "description")
	private String description;
	
	@Field(name = "price")
	private BigDecimal price; 
	
	@Field(name = "quantity")
	private BigInteger quantity;
	
	@Field(name = "picUrl")
	private String picUrl;
}
