package com.assignment.parkinglot.controller;

import com.assignment.parkinglot.service.TicketService;
import io.github.bucket4j.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private Bucket bucket;

    @PostMapping("/create")
    public ResponseEntity<String> createTicket(@RequestParam Long vehicleId,
                                               @RequestParam Long parkingSpaceId) {
        if (bucket.tryConsumeAndReturnRemaining(1).isConsumed()) {
            return ResponseEntity.ok(ticketService.createTicket(vehicleId, parkingSpaceId));
        } else {
            return ResponseEntity.of(Optional.of("Too many requests. Please try again later."));
        }
    }
}