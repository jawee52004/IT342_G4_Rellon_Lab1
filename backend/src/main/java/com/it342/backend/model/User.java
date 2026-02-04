package com.it342.backend.model;

import java.time.LocalDateTime;

public class User {

    private Long user_id;          // you can generate it manually
    private String fullName;
    private String passwordHash;
    private LocalDateTime createdAt;

    public User() {}  // Needed for Firebase deserialization

    public User(String fullName, String passwordHash) {
        this.fullName = fullName;
        this.passwordHash = passwordHash;
        this.createdAt = LocalDateTime.now();
        this.user_id = System.currentTimeMillis(); // simple ID generation
    }

    // Getters and setters
    public Long getUser_id() { return user_id; }
    public void setUser_id(Long user_id) { this.user_id = user_id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
