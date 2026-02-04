package com.it342.backend.model;

public class User {
    private Long id;
    private String fullName;
    private String passwordHash;

    // Default constructor required for Firebase
    public User() {}

    public User(String fullName, String password) {
        this.fullName = fullName;
        this.passwordHash = password; // For now plain text, can hash later
        this.id = System.currentTimeMillis(); // simple unique ID
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
