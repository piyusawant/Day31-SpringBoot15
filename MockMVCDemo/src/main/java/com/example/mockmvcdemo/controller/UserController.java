package com.example.mockmvcdemo.controller;

import com.example.mockmvcdemo.model.User;
import com.example.mockmvcdemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user)
    {
        return new ResponseEntity<>(service.save(user),
        HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id)
    {
        return ResponseEntity.ok(service.getById(id));
    }

}
