package com.assignment.parkinglot.service;

import com.assignment.parkinglot.entity.VehicleEntity;
import com.assignment.parkinglot.factory.VehicleFactory;
import com.assignment.parkinglot.model.Vehicle;
import com.assignment.parkinglot.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public VehicleEntity registerVehicle(String type, String licensePlate) {
        Vehicle vehicleModel = VehicleFactory.createVehicle(type, licensePlate);
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setLicensePlate(vehicleModel.getLicensePlate());
        vehicleEntity.setType(vehicleModel.getType());
        return vehicleRepository.save(vehicleEntity);
    }

    public VehicleEntity getVehicle(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }
}