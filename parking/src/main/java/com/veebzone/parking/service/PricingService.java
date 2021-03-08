package com.veebzone.parking.service;

import com.veebzone.parking.model.Registration;
import com.veebzone.parking.repository.RegistrationRepository;
import com.veebzone.parking.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PricingService {

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    SlotRepository slotRepository;
    public double calculatePricePerMinute(Registration registration) {
        double basePrice = 2.00;
        int vehicleWeight = registration.getVehicle().getWeight();
        int numberOfActiveRegistrations = registrationRepository.getNumberOfActiveRegistrations();
        int numberOfSlots = slotRepository.getNumberOfSlots();

        double occupationPercentage = numberOfActiveRegistrations / numberOfSlots;

        // Takes into account:
        // 1) Vehicle weight
        // 2) Slots occupied
        return (double) vehicleWeight;
    }
}
