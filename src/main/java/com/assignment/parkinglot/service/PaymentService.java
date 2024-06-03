package com.assignment.parkinglot.service;

import com.assignment.parkinglot.entity.ParkingSpaceEntity;
import com.assignment.parkinglot.entity.PaymentEntity;
import com.assignment.parkinglot.entity.TicketEntity;
import com.assignment.parkinglot.factory.PaymentFactory;
import com.assignment.parkinglot.model.Payment;
import com.assignment.parkinglot.repository.ParkingSpaceRepository;
import com.assignment.parkinglot.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    public PaymentEntity processPayment(TicketEntity ticket, String paymentType, BigDecimal amount) {
        ParkingSpaceEntity parkingSpace =
                parkingSpaceRepository.findById(ticket.getParkingSpace().getId()).orElseThrow(() -> new RuntimeException(
                        "Parking space not found"));
        PaymentEntity payment = new PaymentEntity();
        payment.setTicket(ticket);
        payment.setAmount(amount);
        Payment paymentModel = PaymentFactory.createPayment(paymentType, payment);
        paymentModel.processPayment();

        parkingSpace.setOccupied(false);
        parkingSpace.setBookingTime(null);
        parkingSpace.setVehicle(null);
        parkingSpaceRepository.save(parkingSpace);

        return paymentRepository.save(payment);
    }
}