package com.bookshop.productms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.productms.persistence.model.Product;
import com.bookshop.productms.service.IProductService;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@GetMapping(value = "/get/all")
	public ResponseEntity<List<Product>> getAll() {
		return ResponseEntity.ok(productService.getAll());
	}
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") String id) {
		return ResponseEntity.ok(productService.getProduct(id));
	}
	
	@PutMapping(value = "/create")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		return ResponseEntity.ok(productService.createProduct(product));
	}
	
	@PostMapping(value = "/update")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		return ResponseEntity.ok(productService.updateProduct(product));
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") String id) {
		productService.deleteProduct(id);
		return ResponseEntity.ok("Product has been deleted successfully");
	}
}
