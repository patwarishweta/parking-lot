package com.assignment.parkinglot.model;

public class CarParkingSlot extends ParkingSpace {

    public CarParkingSlot() {
        super();
    }

    @Override
    public String getType() {
        return "Car";
    }
}