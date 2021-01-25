package com.bookshop.productms.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
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
	public Product getProduct(ObjectId id) {
		return productRepository.findById(id).get();
	}

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(ObjectId id) {
		productRepository.deleteById(id);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

}
