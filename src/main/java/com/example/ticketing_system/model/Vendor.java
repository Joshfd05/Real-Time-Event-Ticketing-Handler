package com.example.ticketing_system.model;

import com.example.ticketing_system.service.TicketPool;
import com.example.ticketing_system.service.TicketingService;

import java.util.concurrent.TimeUnit;

// Implements Runnable to execute as a thread
public class Vendor implements Runnable {
    private final TicketPool ticketPool;    // Shared resource to manage tickets
    private final int releaseRate;  // Release rate in seconds

    public Vendor(TicketPool ticketPool, int releaseRate) {
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {   // Runs until the thread is interrupted
            // Stop adding tickets if the pool is full
            if (!ticketPool.addTicket(new Ticket("AVAILABLE"))) {
                TicketingService.log("Vendor: Max capacity reached. Stopping ticket addition.");
                break; // Exit the loop if max capacity is reached
            }

            // Log ticket addition in system logs
            TicketingService.log("Vendor added a ticket.");

            try {
                // Use TimeUnit to manage sleep in a more readable way
                TimeUnit.SECONDS.sleep(releaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Ensures interruption is not ignored
                break; // Exit the loop on interruption
            }
        }
    }
}
