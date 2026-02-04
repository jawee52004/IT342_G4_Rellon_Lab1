package com.it342.backend.service;

import com.it342.backend.model.User;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    // Register a new user
    public void register(String fullName, String password) throws ExecutionException, InterruptedException {
        // Create a new User object
        User user = new User(fullName, password);
        // Save user via UserService
        userService.registerUser(user);
    }

    // Login a user
    public boolean login(Long userId, String password) throws ExecutionException, InterruptedException {
        // Retrieve user by ID
        User user = userService.getUserById(userId);
        // Check password match
        return user != null && user.getPasswordHash().equals(password);
    }

    // Get user details
    public User getUserById(Long userId) throws ExecutionException, InterruptedException {
        return userService.getUserById(userId);
    }
}
