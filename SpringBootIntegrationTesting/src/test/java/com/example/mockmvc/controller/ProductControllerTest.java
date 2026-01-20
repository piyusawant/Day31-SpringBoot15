package com.example.mockmvc.controller;

import com.example.springbootintegrationtesting.InApplication;
import com.example.springbootintegrationtesting.controller.ProductController;
import com.example.springbootintegrationtesting.exception.GlobalExceptionHandler;
import com.example.springbootintegrationtesting.model.Product;
import com.example.springbootintegrationtesting.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(ProductControllerTest.class)
@WebMvcTest(ProductController.class)
@ContextConfiguration(classes = InApplication.class)
@Import(GlobalExceptionHandler.class)
public class ProductControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllProducts() throws Exception
    {
        List<Product> products = List.of(new Product(1L, "Laptop", 50000));

        when(productService.getAllProduct()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Laptop"));
    }

    @Test
    void shouldCreateProductSuccessfully() throws Exception {

        String productJson = """
            {
              "name": "Mobile",
              "price": 20000
            }
            """;

        when(productService.createProduct(any(Product.class)))
                .thenReturn(new Product(1L, "Mobile", 20000));

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mobile"));
    }


    @Test
    void shouldReturnBadRequestWhenNameMissing() throws Exception
    {
        Product invalidProduct = new Product(null , "", 20000);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidProduct)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnServerErrorWhenServiceFails() throws Exception
    {
        Product product = new Product(1L, "TV", 30000);

        when(productService.createProduct(any())).thenThrow( new RuntimeException("Service Error"));

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isInternalServerError());
    }



}
