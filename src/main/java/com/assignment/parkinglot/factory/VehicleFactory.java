package com.assignment.parkinglot.factory;

import com.assignment.parkinglot.model.Bike;
import com.assignment.parkinglot.model.Car;
import com.assignment.parkinglot.model.Truck;
import com.assignment.parkinglot.model.Vehicle;

public class VehicleFactory {
    public static Vehicle createVehicle(String type, String licensePlate) {
        Vehicle vehicle;
        switch (type.toUpperCase()) {
            case "CAR":
                vehicle = new Car();
                break;
            case "BIKE":
                vehicle = new Bike();
                break;
            case "TRUCK":
                vehicle = new Truck();
                break;
            default:
                throw new IllegalArgumentException("Unknown vehicle type");
        }
        vehicle.setLicensePlate(licensePlate);
        return vehicle;
    }
}