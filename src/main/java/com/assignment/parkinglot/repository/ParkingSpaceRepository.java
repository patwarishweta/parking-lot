package com.assignment.parkinglot.repository;

import com.assignment.parkinglot.entity.ParkingSpaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpaceEntity, Long> {
    @Query(
            value = "select * from parking_space ps where " +
                    " ps.occupied = :occupied",
            nativeQuery = true
    )
    List<ParkingSpaceEntity> findAvailableParkingSpaces(boolean occupied);
}