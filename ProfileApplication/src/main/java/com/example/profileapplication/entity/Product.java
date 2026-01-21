package com.example.profileapplication.entity;

import jakarta.persistence.*;


@Entity
@Table
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    public Product()
    {
    }
    public Product( String name, double price)
    {
        this.name = name;
        this.price = price;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }




}
