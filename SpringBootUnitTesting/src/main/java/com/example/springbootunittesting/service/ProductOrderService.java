package com.example.springbootunittesting.service;

import com.example.springbootunittesting.model.ProductOrder;
import com.example.springbootunittesting.repository.OrderRepository;

import java.util.List;

public class ProductOrderService
{
    private final OrderRepository repository;

    public ProductOrderService( OrderRepository repository)
    {
        this.repository = repository;
    }

    public ProductOrder createOrder(ProductOrder order)
    {
        if(order.getPrice() <= 0)
        {
            throw new IllegalArgumentException("Invalid Price");

        }
        return repository.save(order);

    }
    public ProductOrder getOrder(Long id)
    {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Order Not Found"));

    }
    public List<ProductOrder> getAllOrders()
    {
        return repository.findAll();
    }
}
