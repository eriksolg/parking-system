package com.veebzone.parking.service;

import com.veebzone.parking.model.Vehicle;
import com.veebzone.parking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public void inserVehicle(Vehicle vehicle) {
    }

    public Optional<Vehicle> getSingleVehicle(Long id) {
        return vehicleRepository.findById(id);
    }

    public void deleteSingleVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
