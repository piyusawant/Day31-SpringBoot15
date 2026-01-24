package com.example.mockmvcdemo.service;

import com.example.mockmvcdemo.model.User;

public interface UserService
{
    User save(User user);
    User getById(Long id);
}
