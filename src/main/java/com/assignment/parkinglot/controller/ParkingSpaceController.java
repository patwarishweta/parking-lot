package com.assignment.parkinglot.controller;

import com.assignment.parkinglot.entity.ParkingSpaceEntity;
import com.assignment.parkinglot.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/parking-space")
public class ParkingSpaceController {
    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @PostMapping("/create")
    public ResponseEntity<String> createParkingSpace(
            @RequestParam String parkingSpaceType) {
        return ResponseEntity.ok(parkingSpaceService.createParkingSpace(parkingSpaceType));
    }

    @PostMapping("/occupy")
    public ResponseEntity<Void> occupySpace(@RequestParam Long id,
                                            @RequestParam Long vehicleId) {
        parkingSpaceService.markSpaceAsOccupied(id, vehicleId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/available-space")
    public ResponseEntity<List<ParkingSpaceEntity>> getAvailableParkingSpaces(
            @RequestParam(required = false) boolean isAvailable
    ) {
        return ResponseEntity.ok(parkingSpaceService.getAvailableParkingSpaces(isAvailable));
    }

    @GetMapping("/all-space")
    public ResponseEntity<List<ParkingSpaceEntity>> getAllParkingSpaces() {
        return ResponseEntity.ok(parkingSpaceService.getAll());
    }

}