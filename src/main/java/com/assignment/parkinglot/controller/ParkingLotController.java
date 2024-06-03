package com.assignment.parkinglot.controller;

import com.assignment.parkinglot.entity.ParkingLotEntity;
import com.assignment.parkinglot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-lot")
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping("create")
    public ResponseEntity<ParkingLotEntity> createParkingLot(
            @RequestParam String location,
            @RequestParam List<Long> parkingSpaceIds) {
        return ResponseEntity.ok(parkingLotService.createParkingLot(location, parkingSpaceIds));
    }

    @GetMapping("/get")
    public ResponseEntity<List<ParkingLotEntity>> getParkingLot(@RequestParam(required = false) Long id) {
        if(id != null) {
            ParkingLotEntity parkingLot = parkingLotService.getParkingLot(id);
            return ResponseEntity.ok(List.of(parkingLot));
        } else {
            return ResponseEntity.ok(parkingLotService.getAllParkingLots());
        }
    }
}