package com.example.ticketing_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"app_user\"")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)   // Ensures this column cannot be null in the database
    private String username;

    @Column(nullable = false)
    private String password;

    // Enum to define possible roles for a user
    public enum Role {
        VENDOR,
        CUSTOMER
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Default Constructor
    public User() {}

    // Constructor wit the parameter parsing
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }
}
