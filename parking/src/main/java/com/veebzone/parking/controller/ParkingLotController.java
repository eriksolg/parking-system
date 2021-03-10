package com.veebzone.parking.controller;

import com.veebzone.parking.dto.SlotDto;
import com.veebzone.parking.model.Floor;
import com.veebzone.parking.model.Slot;
import com.veebzone.parking.service.ParkingLotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "/api/floors")
@RestController
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @ApiOperation(value="Get Floors")
    @GetMapping("/api/floors")
    public List<Floor> getAllFloors() {
        return parkingLotService.getAllFloors();
    }

    @ApiOperation(value="Create Floor")
    @PostMapping("/api/floors")
    @ResponseStatus(HttpStatus.CREATED)
    public Floor insertFloor(@RequestBody Floor floor) {
        return parkingLotService.insertFloor(floor);
    }

    @ApiOperation(value="Get Single Floor")
    @GetMapping("/api/floors/{id}")
    public Floor getSingleFloor(@PathVariable Long id) {
        return parkingLotService.getSingleFloor(id);
    }

    @ApiOperation(value="Delete Floor")
    @DeleteMapping("/api/floors/{id}")
    public void deleteSingleFloor(@PathVariable Long id) {
        parkingLotService.deleteSingleFloor(id);
    }

    @ApiOperation(value="Get Slots on Floor")
    @GetMapping("/api/floors/{id}/slots")
    public List<SlotDto> getSlotsOnFloor(@PathVariable Long id) {
        return parkingLotService.getSlotsOnFloor(id);
    }

    @ApiOperation(value="Get All Slots")
    @GetMapping("/api/floors/slots")
    public List<Slot> getAllSlots() {
        return parkingLotService.getAllSlots();
    }

    @ApiOperation(value="Create Slot")
    @PostMapping("/api/floors/slots")
    @ResponseStatus(HttpStatus.CREATED)
    public Slot insertSlot(@RequestBody Slot slot) {
        return parkingLotService.insertSlot(slot);
    }

    @ApiOperation(value="Get Single Slot")
    @GetMapping("/api/floors/slots/{slotId}")
    public Slot getSingleSlot(@PathVariable Long slotId) {
        return parkingLotService.getSingleSlot(slotId);
    }

    @ApiOperation(value="Delete Slot")
    @DeleteMapping("/api/floors/slots/{slotId}")
    public void deleteSingleSlot(@PathVariable Long slotId) {
        parkingLotService.deleteSingleSlot(slotId);
    }

}
