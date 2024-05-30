package com.assignment.parkinglot.model;

public class CarParkingSlot extends ParkingSlot {

    public CarParkingSlot(String id) {
        super(id);
    }

    @Override
    public String getType() {
        return "Car";
    }
}