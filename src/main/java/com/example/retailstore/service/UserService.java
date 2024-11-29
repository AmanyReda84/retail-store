package com.example.retailstore.service;

import com.example.retailstore.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User addUser(User user);
}
