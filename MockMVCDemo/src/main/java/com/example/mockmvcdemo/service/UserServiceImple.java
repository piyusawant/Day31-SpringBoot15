package com.example.mockmvcdemo.service;

import com.example.mockmvcdemo.exception.UserNotFoundException;
import com.example.mockmvcdemo.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImple implements UserService
{
    private Map<Long, User> userDb = new HashMap<>();

    private Long counter = 1L;

    @Override
    public User save(User user)
    {
        user.setId(counter);
        userDb.put(counter, user);
        counter++;
        return user;
    }

    @Override
    public User getById(Long id)
    {
        User user = userDb.get(id);
        if(user == null)
        {
            throw new UserNotFoundException("User not Found with Id: " +id);
        }
        return user;
    }
}
