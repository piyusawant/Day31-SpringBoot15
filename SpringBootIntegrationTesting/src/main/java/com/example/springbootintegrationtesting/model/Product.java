package com.example.springbootintegrationtesting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "products")

public class Product
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product Name is Required")
    private String name;

    @Min(value = 1, message = "Price must be greater than 0")
    private double price;

    public Product(String name, double price)
    {
        this.name = name;
        this.price = price;

    }
    public Product(Long id, String name, double price)
    {
        this.id = id;
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
