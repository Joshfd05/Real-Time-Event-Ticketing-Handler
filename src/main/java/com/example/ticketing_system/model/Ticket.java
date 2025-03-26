package com.example.ticketing_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Marks this class as a JPA entity for database mapping
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates unique IDs for
    private Long id;
    private String status; // "AVAILABLE", "PURCHASED"

    // Default constructor for JPA
    public Ticket() {
    }

    // Constructor to initialize the ticket with a specific status
    public Ticket(String status) {
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Overrides toString to provide a string representation of the ticket object
    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
