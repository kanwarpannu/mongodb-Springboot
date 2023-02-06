package com.poc.mongodb.repository;

import com.poc.mongodb.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;

@RequiredArgsConstructor
public class CustomProductRepositoryImpl implements CustomProductRepository {

    private final MongoTemplate mongoTemplate;
    @Override
    public void partialUpdate(String productId, String fieldName, Object fieldValue){
        mongoTemplate.findAndModify(BasicQuery.query(Criteria.where("id").is(productId)),
                BasicUpdate.update(fieldName, fieldValue), FindAndModifyOptions.none(), Product.class);
    }
}
