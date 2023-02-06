package com.poc.mongodb.repository;

public interface CustomProductRepository {
    void partialUpdate(String productId, String fieldName, Object fieldValue);
}
