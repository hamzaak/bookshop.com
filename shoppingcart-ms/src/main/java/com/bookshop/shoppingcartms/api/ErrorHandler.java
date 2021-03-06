package com.bookshop.shoppingcartms.api;

import java.math.BigInteger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.shoppingcartms.dto.BaseResponse;
import com.bookshop.shoppingcartms.exception.CustomException;

@ControllerAdvice(annotations = RestController.class)
public class ErrorHandler  {

	@ExceptionHandler(CustomException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public BaseResponse serviceFailed(CustomException ex) {
		BaseResponse response = new BaseResponse();
		response.setStatus(new BigInteger("500"));
		response.setError(ex.getMessage());
		return response;
	}
}
