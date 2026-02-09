package com.it342.backend.service;

import com.it342.backend.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    // Register user
    public void register(String fullName, String password) {
        User user = new User(fullName, password);
        userService.registerUser(user);
    }

    // Login using full name
    public boolean login(String fullName, String password) {
        User user = userService.getUserByFullName(fullName);
        return user != null && user.getPasswordHash().equals(password);
    }

    // Get user by ID
    public User getUserById(Long userId) {
        return userService.getUserById(userId);
    }

    // Get user by full name
    public User getUserByFullName(String fullName) {
        return userService.getUserByFullName(fullName);
    }
}
