package com.example.springbootintegrationtesting.controller;

import com.example.springbootintegrationtesting.model.Product;
import com.example.springbootintegrationtesting.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController
{

    private final ProductService service;
    public ProductController(ProductService service)
    {
        this.service = service;
    }

    @PostMapping
    public Product create(@Valid @RequestBody Product product)
    {
        return service.createProduct(product);
    }

    @GetMapping
    public List<Product> getAll()
    {
        return service.getAllProduct();
    }


}
