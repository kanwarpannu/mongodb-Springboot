package com.poc.mongodb.repository;

import com.poc.mongodb.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String>, CustomProductRepository {
}
