package com.assignment.parkinglot.model;

public class BikeParkingSlot extends ParkingSpace {

    public BikeParkingSlot() {
        super();
    }

    @Override
    public String getType() {
        return "Bike";
    }
}