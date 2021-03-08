package com.veebzone.parking.service;

import com.veebzone.parking.dto.SlotDto;
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

    public void insertFloor(Floor floor) {
        floorRepository.save(floor);
    }

    public Floor getSingleFloor(Long id) {

        Floor floor = floorRepository.findById(id).orElseThrow(NotFoundException::new);
        return floor;
    }

    public void deleteSingleFloor(Long id) {
        floorRepository.deleteById(id);
    }

    public List<SlotDto> getSlotsOnFloor(Long id) {
        Optional<Floor> floor = floorRepository.findFloorByFloorNumber(id);
        List<Slot> slotsOnFloor =  slotRepository.findByFloor(floor);

        List <SlotDto> slotDtos = slotsOnFloor
                .stream().map(slot -> modelMapper.map(slot, SlotDto.class))
                .collect(Collectors.toList());

        return slotDtos;
    }

    public void insertSlot(Slot slot) {
        slotRepository.save(slot);
    }

    public void insertSlot(Long floorId, SlotDto slotDto) {
        Slot slot = new Slot();
        slot.setName(slotDto.getName());
        slot.setFloor(floorRepository.findById(floorId).orElseThrow(NotFoundException::new));
        insertSlot(slot);
    }


    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    public SlotDto getSingleSlot(Long floorId, Long slotId) {
        Slot slot = getSingleSlot(slotId);
        if (slot.getFloor().getId() != floorId) {
            throw new NotFoundException();
        }
        return modelMapper.map(slot, SlotDto.class);
    }

    public Slot getSingleSlot(Long slotId) {
        return slotRepository.findById(slotId).orElseThrow(NotFoundException::new);
    }

    public void deleteSingleSlot(Long floorId, Long slotId) {
        Slot slot = slotRepository.findById(slotId).orElseThrow(NotFoundException::new);
        if (slot.getFloor().getId() != floorId) {
            throw new NotFoundException();
        }
        slotRepository.deleteById(slotId);
    }

}
