package com.it342.backend.service;

import com.it342.backend.model.User;
import com.it342.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register a new user
    public void registerUser(User user) {
        userRepository.save(user);
    }

    // Find user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Find user by full name
    public User getUserByFullName(String fullName) {
        return userRepository.findByFullName(fullName).orElse(null);
    }
}
