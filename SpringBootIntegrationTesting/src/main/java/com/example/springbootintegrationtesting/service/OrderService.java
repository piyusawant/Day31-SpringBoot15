package com.example.springbootintegrationtesting.service;

import com.example.springbootintegrationtesting.model.Order;
import com.example.springbootintegrationtesting.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService
{
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order)
    {
        return orderRepository.save(order);
    }
    public List<Order> getAllOrders()
    {
        return orderRepository.findAll();
    }
}
