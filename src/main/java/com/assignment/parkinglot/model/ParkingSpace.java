package com.assignment.parkinglot.model;

import java.time.LocalDateTime;

public abstract class ParkingSpace {
    private Long vehicleId;
    private boolean occupied;
    private LocalDateTime bookingTime;

    public ParkingSpace() {
        this.occupied = false;
    }

    public abstract String getType();

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}