package com.example.ticketing_system.service;

import com.example.ticketing_system.model.Ticket;
import org.springframework.stereotype.Service;
import com.example.ticketing_system.model.Vendor;
import com.example.ticketing_system.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class TicketingService {
    private final TicketPool ticketPool;
    private ExecutorService executorService;

    // Store logs in a list to expose them to the frontend
    private static final List<String> systemLogs = new ArrayList<>();   // Stores system logs

    public TicketingService(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    // Start the system with vendors and customers
    public void startSystem(int totalTickets, int releaseRate, int purchaseRate, int vendorCount, int customerCount, int maxCapacity) {
        systemLogs.clear(); // Clear logs at the start of the system
        log("Starting ticketing system with %d tickets.", totalTickets);
        log("Release Rate: %ds, Purchase Rate: %ds", releaseRate, purchaseRate);
        log("Vendor Count: %d, Customer Count: %d, Max Capacity: %d", vendorCount, customerCount, maxCapacity);

        ticketPool.setMaxCapacity(maxCapacity); // Sets the maximum capacity for the ticket pool
        log("Max capacity set to: %d", maxCapacity);

        for (int i = 0; i < totalTickets; i++) {
            ticketPool.addTicket(new Ticket("AVAILABLE"));
            log("Ticket added to the pool. Current size: %d", i + 1);
        }

        executorService = Executors.newFixedThreadPool(vendorCount + customerCount);    // Creates a thread pool

        for (int i = 0; i < vendorCount; i++) {
            executorService.submit(new Vendor(ticketPool, releaseRate));    // Adds vendor threads
            log("Vendor thread started.");
        }

        for (int i = 0; i < customerCount; i++) {
            executorService.submit(new Customer(ticketPool, purchaseRate)); // Adds customer threads
            log("Customer thread started.");
        }

        log("Ticketing system started successfully.");
    }

    // Get system status
    public String getSystemStatus() {
        long availableTickets = ticketPool.getAvailableTickets();
        long totalTickets = getTotalTickets(); // Delegates to TicketPool
        int activeThreads = getActiveThreads(); // Gets active threads from executor

        String status = String.format("System Status: Total Tickets: %d, Available Tickets: %d, Active Threads: %d",
                totalTickets, availableTickets, activeThreads);

        log(status);
        return status;
    }

    // Stop the system
    public void stopSystem() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdownNow();
            log("System stopped successfully.");
        } else {
            log("System is not running or already stopped.");
        }
    }

    // Retrieve logs
    public List<String> getSystemLogs() {
        return new ArrayList<>(systemLogs); // Return a copy to avoid modification
    }

    // Add a log message to systemLogs and print it to the terminal
    public static void log(String message, Object... args) {
        String formattedMessage = String.format(message, args);
        systemLogs.add(formattedMessage); // Add to logs
        System.out.println(formattedMessage); // Print to terminal
    }

    // Get total tickets
    public long getTotalTickets() {
        return ticketPool.getTotalTickets();
    }

    // Get active threads
    public int getActiveThreads() {
        if (executorService instanceof ThreadPoolExecutor) {
            return ((ThreadPoolExecutor) executorService).getActiveCount();
        }
        return 0;
    }
}
