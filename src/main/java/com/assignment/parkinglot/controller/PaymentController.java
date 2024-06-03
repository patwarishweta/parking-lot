package com.assignment.parkinglot.controller;

import com.assignment.parkinglot.entity.PaymentEntity;
import com.assignment.parkinglot.entity.TicketEntity;
import com.assignment.parkinglot.service.PaymentService;
import com.assignment.parkinglot.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private TicketService ticketService;

    @PostMapping("/process")
    public ResponseEntity<PaymentEntity> processPayment(@RequestParam Long ticketId, @RequestParam String paymentType,
                                                   @RequestParam BigDecimal amount) {
        TicketEntity ticket = ticketService.getTicket(ticketId);
        PaymentEntity payment = paymentService.processPayment(ticket, paymentType, amount);
        return ResponseEntity.ok(payment);
    }
}