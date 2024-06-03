package com.assignment.parkinglot.util;


import com.assignment.parkinglot.model.BikeParkingSlot;
import com.assignment.parkinglot.model.CarParkingSlot;
import com.assignment.parkinglot.model.ParkingSpace;

public class ParkingSlotFactory {
    public static ParkingSpace createParkingSlot(String type) {
        switch (type.toLowerCase()) {
            case "car":
                return new CarParkingSlot();
            case "bike":
                return new BikeParkingSlot();
            default:
                throw new IllegalArgumentException("Invalid slot type");
        }
    }
}