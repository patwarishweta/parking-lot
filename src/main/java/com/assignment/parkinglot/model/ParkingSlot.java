package com.assignment.parkinglot.model;

public abstract class ParkingSlot {
    private String id;
    private boolean isBooked;
    private long bookingTime;

    public ParkingSlot(String id) {
        this.id = id;
        this.isBooked = false;
    }

    public String getId() {
        return id;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void book() {
        this.isBooked = true;
        this.bookingTime = System.currentTimeMillis();
    }

    public void release() {
        this.isBooked = false;
        this.bookingTime = 0;
    }

    public long getBookingTime() {
        return bookingTime;
    }

    public abstract String getType();
}