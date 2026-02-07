package com.it342.backend.service;

import com.it342.backend.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public void register(String fullName, String password) {
        User user = new User(fullName, password);
        userService.registerUser(user);
    }

    public boolean login(Long userId, String password) {
        User user = userService.getUserById(userId);
        return user != null && user.getPasswordHash().equals(password);
    }

    public User getUserById(Long userId) {
        return userService.getUserById(userId);
    }
}
