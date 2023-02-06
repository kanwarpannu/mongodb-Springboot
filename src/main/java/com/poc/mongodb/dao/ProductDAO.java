package com.poc.mongodb.dao;

import com.poc.mongodb.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDAO {
    private final MongoTemplate mongoTemplate;

    public List<Product> findAll(){
        return mongoTemplate.findAll(Product.class);
    }

    public void saveAll(List<Product> productList){
        mongoTemplate.insertAll(productList);
    }

    public Product findById(String productId){
        return mongoTemplate.findById(productId, Product.class);
    }

    public void partialUpdate(String productId, String fieldName, Object fieldValue){
        mongoTemplate.findAndModify(BasicQuery.query(Criteria.where("id").is(productId)),
                BasicUpdate.update(fieldName, fieldValue), FindAndModifyOptions.none(), Product.class);
    }
}
