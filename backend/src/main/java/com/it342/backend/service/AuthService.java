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
    public void register(String fullName, String email, String password) {
        User user = new User(fullName, email, password);
        userService.registerUser(user);
    }

    // Login using email
    public boolean login(String email, String password) {
        User user = userService.getUserByEmail(email);
        return user != null && user.getPasswordHash().equals(password);
    }

    // Get user by ID
    public User getUserById(Long userId) {
        return userService.getUserById(userId);
    }

    // Get user by email
    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }
}
