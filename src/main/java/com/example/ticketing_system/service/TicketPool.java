package com.example.ticketing_system.service;

import com.example.ticketing_system.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TicketPool {
    private final Queue<Ticket> tickets = new ConcurrentLinkedQueue<>();
    private int maxCapacity = 0;    // Maximum allowed tickets
    private long nextId = 1;  // Counter for ticket IDs

    // Add a ticket to the pool if the current size is less than the max capacity
    public synchronized boolean addTicket(Ticket ticket) {
        if (tickets.size() < maxCapacity) {
            ticket.setId(nextId++); // Set a unique ID for each ticket
            tickets.add(ticket);
            System.out.println("Ticket added to the pool. ID: " + ticket.getId() + ", Current size: " + tickets.size());
            TicketingService.log("Vendor added ticket with ID: " + ticket.getId());
            return true;
        }
        return false; // Return false if max capacity is reached
    }

    // Purchase (consume/remove) a ticket from the pool
    public synchronized boolean purchaseTicket() {
        Ticket ticket = tickets.poll();
        if (ticket != null) {
            System.out.println("Ticket purchased from the pool. ID: " + ticket.getId());
            TicketingService.log("Customer purchased ticket with ID: " + ticket.getId());
            return true;
        } else {
            System.out.println("No tickets available to purchase.");
            return false; // No tickets left to purchase
        }
    }

    // Get the current number of available tickets
    public synchronized long getAvailableTickets() {
        return tickets.size();
    }

    // Get the total tickets (equivalent to available tickets in this implementation)
    public synchronized long getTotalTickets() {
        return tickets.size();
    }

    // Set the maximum capacity for the ticket pool
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        System.out.println("Max capacity set to: " + maxCapacity);
    }

    // Check if the pool is full
    public synchronized boolean isFull() {
        return tickets.size() >= maxCapacity;
    }

    // Check if the pool is empty
    public synchronized boolean isEmpty() {
        return tickets.size() == 0;
    }
}

