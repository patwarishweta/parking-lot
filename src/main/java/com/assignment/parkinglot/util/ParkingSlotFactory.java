package com.assignment.parkinglot.util;


import com.assignment.parkinglot.model.BikeParkingSlot;
import com.assignment.parkinglot.model.CarParkingSlot;
import com.assignment.parkinglot.model.ParkingSlot;

public class ParkingSlotFactory {
    public static ParkingSlot createParkingSlot(String type, String id) {
        switch (type) {
            case "Car":
                return new CarParkingSlot(id);
            case "Bike":
                return new BikeParkingSlot(id);
            default:
                throw new IllegalArgumentException("Invalid slot type");
        }
    }
}