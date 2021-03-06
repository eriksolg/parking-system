package com.veebzone.parking.controller;

import com.veebzone.parking.dto.SlotDto;
import com.veebzone.parking.model.Floor;
import com.veebzone.parking.model.Slot;
import com.veebzone.parking.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @GetMapping("/api/floors")
    public List<Floor> getAllFloors() {
        return parkingLotService.getAllFloors();
    }

    @PostMapping("/api/floors")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertFloor(@RequestBody @Valid Floor floor) {
        parkingLotService.insertFloor(floor);
    }

    @GetMapping("/api/floors/{id}")
    public Optional<Floor> getSingleFloor(@PathVariable Long id) {
        return parkingLotService.getSingleFloor(id);
    }

    @DeleteMapping("/api/floors/{id}")
    public void deleteSingleFloor(@PathVariable Long id) {
        parkingLotService.deleteSingleFloor(id);
    }

    @GetMapping("/api/floors/{id}/slots")
    public List<SlotDto> getSlotsOnFloor(@PathVariable Long id) {
        return parkingLotService.getSlotsOnFloor(id);
    }

    @PostMapping("/api/floors/{floorId}/slots")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertSlot(@PathVariable Long floorId, @RequestBody SlotDto slotDto) {
        parkingLotService.insertSlot(floorId, slotDto);
    }

    @GetMapping("/api/floors/{floorId}/slots/{slotId}")
    public SlotDto getSingleSlot(@PathVariable Long floorId, @PathVariable Long slotId) {
        return parkingLotService.getSingleSlot(floorId, slotId);
    }

    @DeleteMapping("/api/floors/{floorId}/slots/{slotId}")
    public void deleteSingleSlot(@PathVariable Long floorId, @PathVariable Long slotId) {
        parkingLotService.deleteSingleSlot(floorId, slotId);
    }

}
