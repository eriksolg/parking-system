package com.veebzone.parking.service;

import com.veebzone.parking.dto.SlotDto;
import com.veebzone.parking.model.Floor;
import com.veebzone.parking.model.Slot;
import com.veebzone.parking.repository.FloorRepository;
import com.veebzone.parking.repository.SlotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingLotService {

    @Autowired
    FloorRepository floorRepository;

    @Autowired
    SlotRepository slotRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<Floor> getAllFloors() {
        return floorRepository.findAll();
    }

    public void insertFloor(Floor floor) {
        floorRepository.save(floor);
    }

    public Optional<Floor> getSingleFloor(Long id) {
        return floorRepository.findById(id);
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

    public void insertSlot(Long floorId, SlotDto slotDto) {
        Slot slot = new Slot();
        slot.setName(slotDto.getName());
        slot.setFloor(floorRepository.findById(floorId).get());
        slotRepository.save(slot);
    }

    public SlotDto getSingleSlot(Long floorId, Long slotId) {
        Slot slot = slotRepository.findById(slotId).get();
        if (slot.getFloor().getId() != floorId) {
            return null;
        }

        return modelMapper.map(slot, SlotDto.class);
    }

    public void deleteSingleSlot(Long floorId, Long slotId) {
        Slot slot = slotRepository.findById(slotId).get();
        if (slot.getFloor().getId() != floorId) {
            return;
        }
        slotRepository.deleteById(slotId);
    }
}
