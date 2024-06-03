package com.assignment.parkinglot.service;

import com.assignment.parkinglot.entity.ParkingLotEntity;
import com.assignment.parkinglot.entity.ParkingSpaceEntity;
import com.assignment.parkinglot.repository.ParkingLotRepository;
import com.assignment.parkinglot.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingLotService {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    public ParkingLotEntity createParkingLot(String location, List<Long> parkingSpaceIds) {
        ParkingLotEntity parkingLot = new ParkingLotEntity();
        parkingLot.setLocation(location);

        List<ParkingSpaceEntity> parkingSpaces = new ArrayList<>();
        parkingSpaceIds.forEach(parkingSpaceId -> {
            parkingSpaces.add(parkingSpaceRepository.findById(parkingSpaceId).get());
        });

        for (ParkingSpaceEntity parkingSpace : parkingSpaces) {
            parkingSpace.setParkingLot(parkingLot);
        }

        parkingLot.setParkingSpaces(parkingSpaces);
        return parkingLotRepository.saveAndFlush(parkingLot);
    }

    public ParkingLotEntity getParkingLot(Long id) {
        return parkingLotRepository.findById(id).orElseThrow(() -> new RuntimeException("Parking lot not found"));
    }

    public List<ParkingLotEntity> getAllParkingLots() {
        return parkingLotRepository.findAll();
    }
}