package com.example.retailstore.web;
import com.example.retailstore.model.User;
import com.example.retailstore.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUsers() {
        User user1 = new User();
        user1.setId("user1");
        user1.setName("John Doe");

        User user2 = new User();
        user2.setId("user2");
        user2.setName("Jane Doe");

        List<User> users = Arrays.asList(user1, user2);

        when(userService.findAll()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
        verify(userService, times(1)).findAll();
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setId("user1");
        user.setName("John Doe");

        when(userService.addUser(any(User.class))).thenReturn(user);

        ResponseEntity<User> response = userController.addUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).addUser(user);
    }
}

