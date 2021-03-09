package com.veebzone.parking.service;

import com.veebzone.parking.model.Registration;
import com.veebzone.parking.repository.RegistrationRepository;
import com.veebzone.parking.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PricingService {
    @Autowired
    RegistrationRepository registrationRepository;
    @Autowired
    SlotRepository slotRepository;
    @Value("${parkingApplication.basePrice}")
    double basePrice;

    public double calculatePricePerMinute(Registration registration) {
        int vehicleWeight = registration.getVehicle().getWeight();
        int numberOfActiveRegistrations = registrationRepository.getNumberOfActiveRegistrations();
        int numberOfSlots = slotRepository.getNumberOfSlots();

        double occupationPercentage = (double) numberOfActiveRegistrations / (double) numberOfSlots;
        return basePrice + occupationPercentage * ((double) vehicleWeight / 1000);
    }
}
