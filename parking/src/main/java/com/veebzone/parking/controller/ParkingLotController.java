package com.veebzone.parking.controller;

import com.veebzone.parking.dto.SlotDto;
import com.veebzone.parking.model.Floor;
import com.veebzone.parking.service.ParkingLotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public void insertFloor(@RequestBody @Valid Floor floor) {
        parkingLotService.insertFloor(floor);
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

    @ApiOperation(value="Create Slot")
    @PostMapping("/api/floors/{floorId}/slots")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertSlot(@PathVariable Long floorId, @RequestBody SlotDto slotDto) {
        parkingLotService.insertSlot(floorId, slotDto);
    }

    @ApiOperation(value="Get Single Slot")
    @GetMapping("/api/floors/{floorId}/slots/{slotId}")
    public SlotDto getSingleSlot(@PathVariable Long floorId, @PathVariable Long slotId) {
        return parkingLotService.getSingleSlot(floorId, slotId);
    }

    @ApiOperation(value="Delete Slot")
    @DeleteMapping("/api/floors/{floorId}/slots/{slotId}")
    public void deleteSingleSlot(@PathVariable Long floorId, @PathVariable Long slotId) {
        parkingLotService.deleteSingleSlot(floorId, slotId);
    }

}
