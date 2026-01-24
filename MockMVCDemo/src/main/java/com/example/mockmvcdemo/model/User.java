package com.example.mockmvcdemo.model;

import jakarta.validation.constraints.*;

public class User
{
    private Long id;

    @NotNull
    private String name;

    @Email
    private String email;


    public User(Long id, String name, String email)
    {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
