package com.example.ticketing_system.controller;

import com.example.ticketing_system.model.User;
import com.example.ticketing_system.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    // Constructor injection for better dependency management
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {

        // Use of Optional and map to streamline authentication and response handling
        return authService.authenticate(user.getUsername(), user.getPassword())
                .map(authenticatedUser -> "Login successful for role: " + authenticatedUser.getRole())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }


    // Register endpoint
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            authService.saveUser(user);  // Save user details in the database
            return ResponseEntity.ok("User registered successfully as " + user.getRole());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Registration failed: " + e.getMessage());
        }
    }
}
