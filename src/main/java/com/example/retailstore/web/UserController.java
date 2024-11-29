package com.example.retailstore.web;

import com.example.retailstore.model.User;
import com.example.retailstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "User API")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "List User" , description = "Get All User")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>( userService.findAll(), HttpStatus.OK);

    }

    @PostMapping
    @Operation(summary = "Create New User" , description = "Add new user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


}

