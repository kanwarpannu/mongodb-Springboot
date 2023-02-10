package com.poc.mongodb.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.mongodb.dao.ProductDAO;
import com.poc.mongodb.model.Product;
import com.poc.mongodb.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("GIVEN product controller is called")
@AutoConfigureDataMongo
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class DemoApplicationIntegrationTests {

    Product product1 = Product.builder()
            .name("Iphone 12")
            .image("NA")
            .url("NA")
            .brandName("Apple")
            .modelNumber("786238")
            .price(BigDecimal.valueOf(699.99))
            .build();
    Product product2 = Product.builder()
            .name("Iphone 13")
            .image("NA")
            .url("NA")
            .brandName("Apple2")
            .modelNumber("786238123")
            .price(BigDecimal.valueOf(899.99))
            .build();
    List<Product> productList = Arrays.asList(product1, product2);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("WHEN [POST] endpoint on v1 is hit THEN create a new product in mongodb")
    public void createProduct() throws Exception {
        mockMvc.perform(post("/v1/products")
                        .content(objectMapper.writeValueAsString(productList))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn();

        List<Product> actualProductList = productRepository.findAll();
        assertAll(
                () -> assertEquals(productList.get(0).getBrandName(), actualProductList.get(0).getBrandName()),
                () -> assertEquals(productList.get(0).getUrl(), actualProductList.get(0).getUrl()),
                () -> assertEquals(productList.get(0).getPrice(), actualProductList.get(0).getPrice()),
                () -> assertEquals(productList.get(0).getModelNumber(), actualProductList.get(0).getModelNumber()),
                () -> assertEquals(productList.get(0).getName(), actualProductList.get(0).getName()),
                () -> assertEquals(productList.get(1).getBrandName(), actualProductList.get(1).getBrandName()),
                () -> assertEquals(productList.get(1).getUrl(), actualProductList.get(1).getUrl()),
                () -> assertEquals(productList.get(1).getPrice(), actualProductList.get(1).getPrice()),
                () -> assertEquals(productList.get(1).getModelNumber(), actualProductList.get(1).getModelNumber()),
                () -> assertEquals(productList.get(1).getName(), actualProductList.get(1).getName())
        );
    }

    @Test
    @DisplayName("WHEN [POST] endpoint on v2 is hit THEN create a new product in mongodb")
    public void createProductv2() throws Exception {
        mockMvc.perform(post("/v2/products")
                        .content(objectMapper.writeValueAsString(productList))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn();

        List<Product> actualProductList = productDAO.findAll();
        assertAll(
                () -> assertEquals(productList.get(0).getBrandName(), actualProductList.get(0).getBrandName()),
                () -> assertEquals(productList.get(0).getUrl(), actualProductList.get(0).getUrl()),
                () -> assertEquals(productList.get(0).getPrice(), actualProductList.get(0).getPrice()),
                () -> assertEquals(productList.get(0).getModelNumber(), actualProductList.get(0).getModelNumber()),
                () -> assertEquals(productList.get(0).getName(), actualProductList.get(0).getName()),
                () -> assertEquals(productList.get(1).getBrandName(), actualProductList.get(1).getBrandName()),
                () -> assertEquals(productList.get(1).getUrl(), actualProductList.get(1).getUrl()),
                () -> assertEquals(productList.get(1).getPrice(), actualProductList.get(1).getPrice()),
                () -> assertEquals(productList.get(1).getModelNumber(), actualProductList.get(1).getModelNumber()),
                () -> assertEquals(productList.get(1).getName(), actualProductList.get(1).getName())
        );
    }

}

