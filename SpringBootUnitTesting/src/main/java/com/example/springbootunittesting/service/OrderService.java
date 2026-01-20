package com.example.springbootunittesting.service;

import com.example.springbootunittesting.model.OrderItem;
import com.example.springbootunittesting.repository.TaxRepository;

import java.util.List;

public class OrderService
{
    private final TaxRepository taxRepository;

    public OrderService(TaxRepository taxRepository)
    {
        this.taxRepository = taxRepository;
    }

    public double calculateOrderTotal(List<OrderItem> items)
    {
        if(items == null || items.isEmpty())
        {
            throw new IllegalArgumentException("Order Items cannot be Empty");
        }
        double subtotal = 0;
        for(OrderItem item : items)
        {
            if(item.getPrice() <= 0 || item.getQuantity() <= 0)
            {
                throw new IllegalArgumentException("Invalid Item Data");
            }
            subtotal += item.getPrice() * item.getQuantity();
        }

        //Apply Discount
        if(subtotal > 1000)
        {
            subtotal = subtotal * 0.9;
        }

        double taxRate = taxRepository.getTaxRate();
        return subtotal + (subtotal * taxRate);
    }
}
