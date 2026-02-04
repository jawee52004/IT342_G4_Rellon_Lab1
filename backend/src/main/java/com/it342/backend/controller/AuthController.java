package com.it342.backend.controller;

import com.it342.backend.model.User;
import com.it342.backend.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Register a new user
    @PostMapping("/register")
    public String register(@RequestParam String fullName, @RequestParam String password) {
        try {
            authService.register(fullName, password);
            return "User registered successfully!";
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return "Registration failed: " + e.getMessage();
        }
    }

    // Login a user
    @PostMapping("/login")
    public String login(@RequestParam Long userId, @RequestParam String password) {
        try {
            boolean success = authService.login(userId, password);
            if (success) {
                User user = authService.getUserById(userId);
                return "Login successful! Welcome " + user.getFullName() + " (ID: " + user.getId() + ")";
            } else {
                return "Invalid user ID or password.";
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return "Login failed: " + e.getMessage();
        }
    }

    // Get user info by ID
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        try {
            return authService.getUserById(id);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
