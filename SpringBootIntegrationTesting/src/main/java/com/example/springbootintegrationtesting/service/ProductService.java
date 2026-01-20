package com.example.springbootintegrationtesting.service;

import com.example.springbootintegrationtesting.model.Product;

import java.util.List;

public interface ProductService
{
    Product createProduct(Product product);

    List<Product> getAllProduct();
}
