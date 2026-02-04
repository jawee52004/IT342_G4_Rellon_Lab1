package com.it342.backend.controller;

import com.it342.backend.model.User;
import com.it342.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // allow frontend
public class AuthController {

    private final AuthService authService;

    // Simple in-memory session storage for lab purposes
    private final Map<Long, Boolean> sessions = new HashMap<>();

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            authService.register(user.getFullName(), user.getPasswordHash());
            return ResponseEntity.ok("User registered successfully!");
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error registering user");
        }
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            boolean success = authService.login(user.getUser_id(), user.getPasswordHash());
            if (success) {
                sessions.put(user.getUser_id(), true); // mark user as logged in
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error logging in");
        }
    }

    // DASHBOARD
    @GetMapping("/dashboard/{userId}")
    public ResponseEntity<User> dashboard(@PathVariable Long userId) {
        if (sessions.getOrDefault(userId, false)) {
            try {
                User user = authService.getUserById(userId);
                if (user != null) {
                    return ResponseEntity.ok(user);
                } else {
                    return ResponseEntity.notFound().build();
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).build();
            }
        } else {
            return ResponseEntity.status(401).build(); // user not logged in
        }
    }

    // LOGOUT
    @PostMapping("/logout/{userId}")
    public ResponseEntity<String> logout(@PathVariable Long userId) {
        sessions.remove(userId);
        return ResponseEntity.ok("Logged out successfully");
    }
}
