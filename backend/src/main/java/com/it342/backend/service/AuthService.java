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

    // Register new user
    public void register(String fullName, String password) throws ExecutionException, InterruptedException {
        User user = new User(fullName, password);
        userService.registerUser(user);
    }

    // Login user
    public boolean login(Long userId, String password) throws ExecutionException, InterruptedException {
        User user = userService.getUserById(userId);
        return user != null && user.getPasswordHash().equals(password);
    }

    // Get user for dashboard
    public User getUserById(Long userId) throws ExecutionException, InterruptedException {
        return userService.getUserById(userId);
    }
}
