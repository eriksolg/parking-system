package com.veebzone.parking.service;

import com.veebzone.parking.exception.DuplicateValueException;
import com.veebzone.parking.exception.InUseException;
import com.veebzone.parking.exception.NotFoundException;
import com.veebzone.parking.model.Slot;
import com.veebzone.parking.model.Vehicle;
import com.veebzone.parking.repository.RegistrationRepository;
import com.veebzone.parking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    RegistrationRepository registrationRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle insertVehicle(Vehicle vehicle) {
        if (vehicleRepository.findByRegNumber(vehicle.getRegistrationNumber()) != null)
            throw new DuplicateValueException();

        return vehicleRepository.save(vehicle);
    }

    public Vehicle getSingleVehicle(Long id) {
        return vehicleRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void deleteSingleVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(NotFoundException::new);
        if (registrationRepository.findRegistrationsByVehicle(vehicle).size() != 0)
            throw new InUseException();

        vehicleRepository.deleteById(id);
    }
}
