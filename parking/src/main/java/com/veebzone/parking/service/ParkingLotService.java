package com.veebzone.parking.service;

import com.veebzone.parking.dto.SlotDto;
import com.veebzone.parking.exception.DuplicateValueException;
import com.veebzone.parking.exception.InUseException;
import com.veebzone.parking.exception.NotFoundException;
import com.veebzone.parking.model.Floor;
import com.veebzone.parking.model.Slot;
import com.veebzone.parking.repository.FloorRepository;
import com.veebzone.parking.repository.RegistrationRepository;
import com.veebzone.parking.repository.SlotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingLotService {
    @Autowired
    FloorRepository floorRepository;
    @Autowired
    SlotRepository slotRepository;
    @Autowired
    RegistrationRepository registrationRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<Floor> getAllFloors() {
        return floorRepository.findAll();
    }

    public Floor insertFloor(Floor floor) {
        if (floorRepository.findFloorByFloorNumber(floor.getFloorNumber()) != null)
            throw new DuplicateValueException();

        return floorRepository.save(floor);
    }

    public Floor getSingleFloor(Long id) {
        return floorRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void deleteSingleFloor(Long id) {
        Floor floor = floorRepository.findById(id).orElseThrow(NotFoundException::new);
        if (slotRepository.findByFloor(floor) != null)
            throw new InUseException();

        floorRepository.deleteById(id);
    }

    public List<SlotDto> getSlotsOnFloor(Long id) {
        Floor floor = floorRepository.findById(id).orElseThrow(NotFoundException::new);
        List<Slot> slotsOnFloor =  slotRepository.findByFloor(floor);

        return slotsOnFloor
                .stream().map(slot -> modelMapper.map(slot, SlotDto.class))
                .collect(Collectors.toList());
    }

    public Slot insertSlot(Slot slot) {
        return slotRepository.save(slot);
    }

    public Slot insertSlot(Long floorId, SlotDto slotDto) {
        Floor floor = floorRepository.findById(floorId).orElseThrow(NotFoundException::new);
        Slot slot = new Slot();
        slot.setName(slotDto.getName());
        slot.setFloor(floor);

        return insertSlot(slot);
    }

    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    public Slot getSingleSlot(Long slotId) {
        return slotRepository.findById(slotId).orElseThrow(NotFoundException::new);
    }

    public void deleteSingleSlot(Long slotId) {
        Slot slot = slotRepository.findById(slotId).orElseThrow(NotFoundException::new);
        if (registrationRepository.findRegistrationsBySlot(slot).size() != 0)
            throw new InUseException();

        slotRepository.deleteById(slotId);
    }
}
