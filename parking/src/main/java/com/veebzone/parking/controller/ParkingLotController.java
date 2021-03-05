package com.veebzone.parking.controller;

import com.veebzone.parking.model.Floor;
import com.veebzone.parking.model.Registration;
import com.veebzone.parking.model.Slot;
import com.veebzone.parking.repository.FloorRepository;
import com.veebzone.parking.repository.RegistrationRepository;
import com.veebzone.parking.repository.SlotRepository;
import com.veebzone.parking.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @GetMapping("/api/floors")
    public List<Floor> getAllFloors() {
        return parkingLotService.getAllFloors();
    }

    @GetMapping("/api/floors/{id}/slots")
    public List<Slot> getSlotsOnFloor(@PathVariable Long id) {
        return parkingLotService.getSlotsOnFloor(id);
    }

}
