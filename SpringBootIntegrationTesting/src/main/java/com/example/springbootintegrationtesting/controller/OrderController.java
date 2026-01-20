package com.example.springbootintegrationtesting.controller;

import com.example.springbootintegrationtesting.model.Order;
import com.example.springbootintegrationtesting.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController
{
    private final OrderService service;

    public OrderController(OrderService service)
    {
        this.service = service;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order)
    {
        return service.saveOrder(order);
    }

    @GetMapping
    public List<Order> getOrders()
    {
        return service.getAllOrders();
    }

}
