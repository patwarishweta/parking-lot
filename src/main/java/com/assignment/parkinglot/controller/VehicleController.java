package com.assignment.parkinglot.controller;

import com.assignment.parkinglot.entity.VehicleEntity;
import com.assignment.parkinglot.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/register")
    public ResponseEntity<VehicleEntity> registerVehicle(@RequestParam String type, @RequestParam String licensePlate) {
        VehicleEntity vehicle = vehicleService.registerVehicle(type, licensePlate);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<VehicleEntity> getVehicle(@RequestParam Long id) {
        return ResponseEntity.ok(vehicleService.getVehicle(id));
    }
}