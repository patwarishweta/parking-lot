package com.assignment.parkinglot.controller;

import com.assignment.parkinglot.model.ParkingSlot;
import com.assignment.parkinglot.service.ParkingLotService;
import io.github.bucket4j.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/parking")
public class ParkingLotController {

    @Autowired
    private Bucket bucket;
    @Autowired
    ParkingLotService parkingLotService;

    @GetMapping("/available-slots")
    public Map<String, ParkingSlot> getAvailableSlots() {
        return parkingLotService.getAvailableSlots();
    }

    @PostMapping("/book-slots")
    public CompletableFuture<String> bookSlots(@RequestBody List<String> ids) {
        if (bucket.tryConsumeAndReturnRemaining(1).isConsumed()) {
            return parkingLotService.bookSlotsAsync(ids)
                    .thenApply(successIds -> successIds.isEmpty() ? "Slot booking failed" : "Slots booked successfully: " + String.join(", ", successIds));
        } else {
            return CompletableFuture.completedFuture("Too many requests. Please try again later.");
        }
    }

    @GetMapping("/all-slots")
    public Map<String, ParkingSlot> getAllSlots() {
        return parkingLotService.getAllSlots();
    }
}