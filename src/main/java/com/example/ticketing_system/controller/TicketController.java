package com.example.ticketing_system.controller;

import com.example.ticketing_system.service.TicketingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticketing")
public class TicketController {

    private final TicketingService ticketingService;

    // Constructor injection for better testability
    public TicketController(TicketingService ticketingService) {
        this.ticketingService = ticketingService;
    }

    // Start the system with specified parameters
    @PostMapping("/start")
    public ResponseEntity<String> startSystem(
            @RequestParam int totalTickets,
            @RequestParam int releaseRate,
            @RequestParam int purchaseRate,
            @RequestParam int vendorCount,
            @RequestParam int customerCount,
            @RequestParam int maxCapacity) {
        try {
            // Validate purchase rate to ensure system stability
            if (purchaseRate > releaseRate) {
                return ResponseEntity.badRequest().body("Error: Customer purchase rate should be lower than the vendor ticket addition rate.");
            }
            // Initialize the system with provided parameters
            ticketingService.startSystem(totalTickets, releaseRate, purchaseRate, vendorCount, customerCount, maxCapacity);
            return ResponseEntity.ok("System initialized successfully.");
        } catch (Exception e) {
            // Return error message if initialization fails
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // Fetch the system status
    @GetMapping("/status")
    public ResponseEntity<String> getSystemStatus() {
        try {
            String status = ticketingService.getSystemStatus(); // Fetch status details from the service
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // Stop the system
    @PostMapping("/stop")
    public ResponseEntity<String> stopSystem() {
        try {
            ticketingService.stopSystem();
            return ResponseEntity.ok("System stopped successfully.");   // Gracefully stop all threads and processes
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // Fetch system logs
    @GetMapping("/logs")
    public ResponseEntity<List<String>> getLogs() {
        try {
            List<String> logs = ticketingService.getSystemLogs();
            return ResponseEntity.ok(logs);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
