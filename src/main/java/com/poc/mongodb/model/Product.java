package com.poc.mongodb.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document
@Getter
@Setter
public class Product {
    @Id
    private String id;

    private String name;

    private String modelNumber;

    private String brandName;

    private String url;

    @Field("imageUrl")
    private String image;

    private BigDecimal price;
}
