package com.bookshop.productms.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.productms.persistence.IProductRepository;
import com.bookshop.productms.persistence.model.Product;
import com.bookshop.productms.service.IProductService;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	private IProductRepository productRepository;

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(String id) {
		return productRepository.findById(id).get();
	}

	@Override
	public Product createProduct(Product product) {
		product.setId(UUID.randomUUID().toString());
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(String id) {
		productRepository.deleteById(id);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

}
