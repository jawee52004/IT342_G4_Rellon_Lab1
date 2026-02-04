package com.it342.backend.service;

import com.it342.backend.model.User;
import com.google.firebase.database.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private final DatabaseReference database;

    public UserService() {
        this.database = FirebaseDatabase.getInstance().getReference("users");
    }

    // Register user
    public void registerUser(User user) throws InterruptedException, ExecutionException {
        DatabaseReference userRef = database.child(String.valueOf(user.getUser_id()));
        userRef.setValueAsync(user).get(); // synchronous write
    }

    // Get user by ID
    public User getUserById(Long userId) throws InterruptedException, ExecutionException {
        CompletableFuture<User> future = new CompletableFuture<>();

        database.child(String.valueOf(userId)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                future.complete(user);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                future.completeExceptionally(new RuntimeException("Firebase error: " + error.getMessage()));
            }
        });

        // Wait for the listener to complete
        return future.get();
    }
}
