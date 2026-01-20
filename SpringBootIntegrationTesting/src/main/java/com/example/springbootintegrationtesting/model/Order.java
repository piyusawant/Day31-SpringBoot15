package com.example.springbootintegrationtesting.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    public Order()
    {

    }
    public Order(String name, double price)
    {
        this.name = name;
        this.price = price;
    }

    public Long getId()
    {
        return id;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
