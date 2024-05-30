package com.assignment.parkinglot.util;

import com.assignment.parkinglot.model.ParkingSlot;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

@Component
public class SlotExpiryObserver {

    public void observe(ParkingSlot slot, Runnable callback) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (System.currentTimeMillis() - slot.getBookingTime() >= 2 * 60 * 60 * 1000) {
                    slot.release();
                    callback.run();
                    timer.cancel();
                }
            }
        }, 0, 60 * 1000);
    }
}