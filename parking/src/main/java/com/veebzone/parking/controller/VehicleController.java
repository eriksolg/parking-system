package com.veebzone.parking.controller;
import com.veebzone.parking.model.Vehicle;
import com.veebzone.parking.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping("/api/vehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @PostMapping("/api/vehicles")
    @ResponseStatus(HttpStatus.CREATED)
    public void inserVehicle(@RequestBody @Valid Vehicle vehicle) {
        vehicleService.inserVehicle(vehicle);
    }

    @GetMapping("/api/vehicles/{id}")
    public Vehicle getSingleVehicle(@PathVariable Long id) {
        return vehicleService.getSingleVehicle(id);
    }

    @DeleteMapping("/api/vehicles/{id}")
    public void deleteSingleVehicle(@PathVariable Long id) {
        vehicleService.deleteSingleVehicle(id);
    }
}
