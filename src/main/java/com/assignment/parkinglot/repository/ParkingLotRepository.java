package com.assignment.parkinglot.repository;

import com.assignment.parkinglot.entity.ParkingLotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLotEntity, Long> {
}