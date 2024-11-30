package com.example.retailstore.service;

import com.example.retailstore.dao.UserRepository;
import com.example.retailstore.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        User user1 = new User();
        user1.setId("user1");
        user1.setName("John Doe");

        User user2 = new User();
        user2.setId("user2");
        user2.setName("Jane Doe");

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.findAll();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setId("user1");
        user.setName("John Doe");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.addUser(user);

        assertEquals("John Doe", result.getName());
        verify(userRepository, times(1)).save(user);
    }
}

