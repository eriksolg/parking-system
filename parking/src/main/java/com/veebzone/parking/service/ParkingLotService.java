package com.veebzone.parking.service;

import com.veebzone.parking.model.Floor;
import com.veebzone.parking.model.Slot;
import com.veebzone.parking.repository.FloorRepository;
import com.veebzone.parking.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    @Autowired
    FloorRepository floorRepository;

    @Autowired
    SlotRepository slotRepository;

    public List<Floor> getAllFloors() {
        return floorRepository.findAll();
    }

    public List<Slot> getSlotsOnFloor(Long id) {
        return slotRepository.findAll();
    }
}
