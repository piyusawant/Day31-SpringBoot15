package com.example.springbootunittesting.repository;

import com.example.springbootunittesting.model.ProductOrder;

import java.util.List;
import java.util.Optional;

public interface OrderRepository
{
    ProductOrder save(ProductOrder productOrder);
    Optional<ProductOrder> findById(Long id);
    List<ProductOrder> findAll();
}
