package com.assignment.parkinglot.service;

import com.assignment.parkinglot.entity.ParkingSpaceEntity;
import com.assignment.parkinglot.entity.TicketEntity;
import com.assignment.parkinglot.entity.VehicleEntity;
import com.assignment.parkinglot.repository.ParkingSpaceRepository;
import com.assignment.parkinglot.repository.TicketRepository;
import com.assignment.parkinglot.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public String createTicket(Long vehicleId, Long parkingSpaceId) {
        VehicleEntity vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() ->
                new RuntimeException("Vehicle not found"));
        ParkingSpaceEntity parkingSpace =
                parkingSpaceRepository.findById(parkingSpaceId).orElseThrow(() -> new RuntimeException("Parking space not found"));

        if (parkingSpace.isOccupied()) {
            throw new RuntimeException("Parking space is already occupied");
        }

        if(!parkingSpace.getParkingSpaceType().toLowerCase().equals(vehicle.getType().toLowerCase())) {
            throw new RuntimeException("This vehicle cannot be parked at this slot, please choose appropriate " +
                    "parking slot");
        }

        TicketEntity ticket = new TicketEntity();
        ticket.setVehicle(vehicle);
        ticket.setParkingSpace(parkingSpace);
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setExpiryTime(LocalDateTime.now().plusHours(2));
        ticket.setExpired(false);

        parkingSpace.setOccupied(true);
        parkingSpace.setBookingTime(LocalDateTime.now());
        parkingSpaceRepository.save(parkingSpace);

        try {
            ticketRepository.save(ticket);
            return "ok";
        } catch(RuntimeException e) {
            throw new RuntimeException("Exception occurred while booking the slot");
        }
    }

    @Scheduled(fixedRate = 60000) // Run every hour
    public void expireTickets() {
        LocalDateTime now = LocalDateTime.now();
        ticketRepository.findAll().forEach(ticket -> {
            if (ticket.getExpiryTime().isBefore(now) && !ticket.isExpired()) {
                ticket.setExpired(true);
                ticket.getParkingSpace().setOccupied(false);
                ticket.getParkingSpace().setBookingTime(null);
                ticket.getParkingSpace().setVehicle(null);
                parkingSpaceRepository.save(ticket.getParkingSpace());

                ticketRepository.save(ticket);
            }
        });
    }

    public TicketEntity getTicket(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
    }
}