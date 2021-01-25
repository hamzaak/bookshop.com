package com.bookshop.productms.persistence;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bookshop.productms.persistence.model.Product;

@Repository
public interface IProductRepository extends MongoRepository<Product, ObjectId>{

}
