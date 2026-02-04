package com.it342.backend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        // Load the service account JSON from resources using ClassLoader
        FileInputStream serviceAccount = new FileInputStream(
                getClass().getClassLoader().getResource("firebase/serviceAccountKey.json").getFile()
        );

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                // Replace with your Firebase Realtime Database URL
                .setDatabaseUrl("https://console.firebase.google.com/project/miniapp-5debf/database/miniapp-5debf-default-rtdb/data/~2F")
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
