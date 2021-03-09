package com.veebzone.parking.service;

import com.veebzone.parking.model.Floor;
import com.veebzone.parking.model.Registration;
import com.veebzone.parking.model.Slot;
import com.veebzone.parking.repository.RegistrationRepository;
import com.veebzone.parking.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequestScope
public class SlotAssignmentService {
    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    SlotRepository slotRepository;

    List<Registration> activeRegistrations;
    List<Slot> availableSlots;
    Map<Long, Integer> floorWeightUsages;

    public Slot findCompatibleSlot(Registration registration) {
        findActiveRegistrations();
        findAvailableSlots();

        List<Floor> availableFloors = findCompatibleFloors(registration);
        Floor floorWithMinWeightUsage = getFloorWithMinWeightUsage(availableFloors);

        availableSlots.stream().filter(slot -> slot.getFloor().getId().equals(floorWithMinWeightUsage.getId()));

        return availableSlots.size() != 0 ? availableSlots.get(0) : null;
    }

    private Floor getFloorWithMinWeightUsage(List<Floor> availableFloors) {
        Floor floorWithMinWeightUsage = null;
        int currentMinWeightUsage = Integer.MAX_VALUE;

        for (Floor floor : availableFloors) {
            int floorWeightUsage = floorWeightUsages.get(floor.getId());
            if (floorWeightUsage < currentMinWeightUsage) {
                currentMinWeightUsage = floorWeightUsage;
                floorWithMinWeightUsage = floor;
            }
        }

        return floorWithMinWeightUsage;
    }

    private List<Floor> findCompatibleFloors(Registration registration) {
        List<Floor> availableFloors = new ArrayList<>();

        for (Slot slot : availableSlots) {
            if (availableFloors.contains(slot.getFloor()))
                continue;
            availableFloors.add(slot.getFloor());
        }

        floorWeightUsages = new HashMap<>();

        availableFloors.removeIf(floor -> floor.getHeight() <= registration.getVehicle().getHeight());

        for (Floor floor : availableFloors) {
            int currentWeightUsage = 0;
            for (Registration activeRegistration : activeRegistrations) {
                if (activeRegistration.getSlot().getFloor() == floor)
                    currentWeightUsage += activeRegistration.getVehicle().getWeight();
            }
            floorWeightUsages.put(floor.getId(), currentWeightUsage);
        }
        availableFloors.removeIf(floor -> floorWeightUsages.get(floor.getId()) + registration.getVehicle().getWeight() >= floor.getWeightCapacity());

        return availableFloors;
    }

    private void findActiveRegistrations() {
        activeRegistrations = registrationRepository.findActiveRegistrations();
    }

    public List<Slot> findAvailableSlots() {
        List<Long> occupiedSlotsIds = activeRegistrations.stream().map(activeReg -> activeReg.getSlot().getId()).collect(Collectors.toList());
        return slotRepository.findByIdExcluded(occupiedSlotsIds);
    }
}
