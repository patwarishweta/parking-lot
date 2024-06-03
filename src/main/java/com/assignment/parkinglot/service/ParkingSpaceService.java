package com.assignment.parkinglot.service;

import com.assignment.parkinglot.entity.ParkingLotEntity;
import com.assignment.parkinglot.entity.ParkingSpaceEntity;
import com.assignment.parkinglot.model.ParkingSpace;
import com.assignment.parkinglot.repository.ParkingSpaceRepository;
import com.assignment.parkinglot.repository.VehicleRepository;
import com.assignment.parkinglot.util.ParkingSlotFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ParkingSpaceService {
    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public String createParkingSpace(String parkingSpaceType) {
        ParkingSpace parkingSpace = ParkingSlotFactory.createParkingSlot(parkingSpaceType);
        ParkingSpaceEntity parkingSpaceEntity = ParkingSpaceEntity.builder()
                .vehicle(null)
                .occupied(parkingSpace.isOccupied())
                .bookingTime(parkingSpace.getBookingTime())
                .parkingSpaceType(parkingSpaceType)
                .build();
        try {
            parkingSpaceRepository.saveAndFlush(parkingSpaceEntity);
            return "created";
        } catch(Exception e) {
            log.error("Could not create parking space." + e.getMessage());
        }
        return "Failed";
    }
    
    public void markSpaceAsOccupied(Long spaceId, Long vehicleId) {
        ParkingSpaceEntity space = parkingSpaceRepository.findById(spaceId)
                              .orElseThrow(() -> new NotFoundException("Parking space not found"));
        space.setOccupied(true);
        space.setBookingTime(LocalDateTime.now());
        space.setVehicle(vehicleRepository.getById(vehicleId));
        parkingSpaceRepository.save(space);
    }

    public List<ParkingSpaceEntity> getAll() {
        return parkingSpaceRepository.findAll();
    }

    public List<ParkingSpaceEntity> getAvailableParkingSpaces(boolean isAvailable) {
        return parkingSpaceRepository.findAvailableParkingSpaces(!isAvailable);
    }
}