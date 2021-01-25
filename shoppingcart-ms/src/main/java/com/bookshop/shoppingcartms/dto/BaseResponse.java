package com.bookshop.shoppingcartms.dto;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
	private BigInteger status;
	private String error;
}
