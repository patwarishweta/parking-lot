package com.assignment.parkinglot.service;

import com.assignment.parkinglot.model.ParkingSlot;
import com.assignment.parkinglot.util.ParkingSlotFactory;
import com.assignment.parkinglot.util.SlotExpiryObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@EnableAsync
public class ParkingLotService {
    private static ParkingLotService instance;
    private final Map<String, ParkingSlot> slots = new ConcurrentHashMap<>();

    @Autowired
    SlotExpiryObserver observer;

    public ParkingLotService() {
        for (int i = 1; i <= 50; i=i+2) {
            slots.put(("C" + i), ParkingSlotFactory.createParkingSlot("Car", "C" + i));
            slots.put(("B" + (i+1)), ParkingSlotFactory.createParkingSlot("Bike", "B" + (i+1)));
        }
    }

    public static synchronized ParkingLotService getInstance() {
        if (instance == null) {
            instance = new ParkingLotService();
        }
        return instance;
    }

    public Map<String, ParkingSlot> getAvailableSlots() {
        return slots.entrySet().stream()
                .filter(entry -> !entry.getValue().isBooked())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Async
    public CompletableFuture<List<String>> bookSlotsAsync(List<String> ids) {
        List<String> bookedSlots = ids.stream()
                .filter(id -> {
                    ParkingSlot slot = slots.get(id);
                    if (slot != null && !slot.isBooked()) {
                        synchronized (slot) {
                            if (slot != null && !slot.isBooked()) {
                                slot.book();
                                observer.observe(slot, () -> System.out.println("Slot " + id + " is now free."));
                                return true;
                            }
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
        return CompletableFuture.completedFuture(bookedSlots);
    }

    public Map<String, ParkingSlot> getAllSlots() {
        return slots;
    }
}