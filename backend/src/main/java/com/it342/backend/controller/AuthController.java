package com.it342.backend.controller;

import com.it342.backend.model.User;
import com.it342.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;
    private final Map<Long, Boolean> sessions = new HashMap<>();

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        authService.register(user.getFullName(), user.getPasswordHash());
        return ResponseEntity.ok("User registered successfully");
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean success = authService.login(user.getFullName(), user.getPasswordHash());

        if (success) {
            // Fetch user to get ID for session storage
            User loggedInUser = authService.getUserByFullName(user.getFullName());
            sessions.put(loggedInUser.getId(), true);
            return ResponseEntity.ok("Login successful");
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }

    // DASHBOARD
    @GetMapping("/dashboard/{userId}")
    public ResponseEntity<User> dashboard(@PathVariable Long userId) {
        if (!sessions.getOrDefault(userId, false)) {
            return ResponseEntity.status(401).build();
        }

        User user = authService.getUserById(userId);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    // LOGOUT
    @PostMapping("/logout/{userId}")
    public ResponseEntity<String> logout(@PathVariable Long userId) {
        sessions.remove(userId);
        return ResponseEntity.ok("Logged out successfully");
    }
}
