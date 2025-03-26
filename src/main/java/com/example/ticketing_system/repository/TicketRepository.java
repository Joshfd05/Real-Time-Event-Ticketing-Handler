package com.example.ticketing_system.repository;

import com.example.ticketing_system.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Example;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Find first ticket with the given status
    default Optional<Ticket> findFirstByStatus(String status) {
        Ticket probe = new Ticket();
        probe.setStatus(status);
        return findOne(Example.of(probe));  // Uses Example API to query the database
    }

    // Count tickets by status
    default long countByStatus(String status) {
        Ticket probe = new Ticket();
        probe.setStatus(status);
        return findAll(Example.of(probe)).size();   // Finds all matching records and returns the count
    }
}
