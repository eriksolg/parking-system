package com.veebzone.parking.controller;
import com.veebzone.parking.model.Vehicle;
import com.veebzone.parking.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "/api/vehicles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @ApiOperation(value="Get Vehicles")
    @GetMapping("/api/vehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @ApiOperation(value="Create Vehicle")
    @PostMapping("/api/vehicles")
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle inserVehicle(@RequestBody @Valid Vehicle vehicle) {
        return vehicleService.insertVehicle(vehicle);
    }

    @ApiOperation(value="Get Single Vehicle")
    @GetMapping("/api/vehicles/{id}")
    public Vehicle getSingleVehicle(@PathVariable Long id) {
        return vehicleService.getSingleVehicle(id);
    }

    @ApiOperation(value="Delete Vehicle")
    @DeleteMapping("/api/vehicles/{id}")
    public void deleteSingleVehicle(@PathVariable Long id) {
        vehicleService.deleteSingleVehicle(id);
    }
}
