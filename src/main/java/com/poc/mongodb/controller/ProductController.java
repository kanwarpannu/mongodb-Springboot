package com.poc.mongodb.controller;

import com.poc.mongodb.model.Product;
import com.poc.mongodb.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @PostMapping("/products")
    public void createProducts(@RequestBody List<Product> products) {
        productRepository.saveAll(products);
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{productId}")
    public Product getProduct(@PathVariable String productId) {
        return productRepository.findById(productId).orElse(Product.builder().build());
    }

    @PatchMapping("/products/{productId}")
    public void partialUpdate(@PathVariable String productId, @RequestBody Product product) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (Field field : Product.class.getDeclaredFields()) {
            String fieldName = field.getName();

            if (fieldName.equals("id")) {
                continue;
            }
            Method getterMethod = Product.class.getMethod("get" + StringUtils.capitalize(fieldName));
            Object fieldValue = getterMethod.invoke(product);

            if (Objects.nonNull(fieldValue)) {
                productRepository.partialUpdate(productId, fieldName, fieldValue);
            }
        }
    }
}
