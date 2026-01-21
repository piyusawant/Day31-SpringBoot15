package com.example.profileapplication.service;

import com.example.profileapplication.entity.Product;
import com.example.profileapplication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService
{
    @Autowired
    private ProductRepository repository;

    public Product save(Product product)
    {
        return repository.save(product);
    }

    public Long count()
    {
        return repository.count();
    }
}
