package com.bookshop.shoppingcartms.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.bson.types.ObjectId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
	private ObjectId id;
	private String title;
	private String description;
	private BigDecimal price;
	private BigInteger quantity;
}
