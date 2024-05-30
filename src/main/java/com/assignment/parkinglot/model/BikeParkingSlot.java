package com.assignment.parkinglot.model;

public class BikeParkingSlot extends ParkingSlot {

    public BikeParkingSlot(String id) {
        super(id);
    }

    @Override
    public String getType() {
        return "Bike";
    }
}