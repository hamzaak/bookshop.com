package com.bookshop.shoppingcartms.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public CustomException(String message) {
		super(message);
	}

}
