package com.bookshop.shoppingcartms.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookshop.shoppingcartms.dto.ProductDTO;

@FeignClient(value = "product-ms", url = "http://productservice:9001/api/products")
public interface IProductServiceClient {
	 
	@GetMapping(value = "/get/{id}")
	ProductDTO getProduct(@PathVariable("id") String id);
	
}
