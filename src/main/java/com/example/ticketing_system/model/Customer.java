package com.example.ticketing_system.model;

import com.example.ticketing_system.service.TicketPool;
import com.example.ticketing_system.service.TicketingService;

import java.util.concurrent.TimeUnit;

// Customer class simulates a customer who purchases tickets at a specified rate
public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int purchaseRate;  // Purchase rate in seconds

    public Customer(TicketPool ticketPool, int purchaseRate) {
        this.ticketPool = ticketPool;
        this.purchaseRate = purchaseRate;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            // Try purchasing a ticket from the pool
            if (ticketPool.purchaseTicket()) {
                // Log customer purchase
                TicketingService.log("Customer purchased a ticket.");
            } else {
                // Log when no tickets are available
                TicketingService.log("Customer: No tickets available for purchase.");
                break; // Exit the loop if no tickets are available
            }

            try {
                TimeUnit.SECONDS.sleep(purchaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Ensure interruption is not ignored
                break; // Exit the loop on interruption
            }
        }
    }
}


