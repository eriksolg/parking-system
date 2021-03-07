package com.veebzone.parking.service;

import com.veebzone.parking.model.Registration;
import org.springframework.stereotype.Service;

@Service
public class PricingService {
    public double calculatePricePerMinute(Registration registration) {
        int vehicleWeight = registration.getVehicle().getWeight();

        // Takes into account:
        // 1) Vehicle weight
        // 2) Slots occupied
        return (double) vehicleWeight;
    }
}
