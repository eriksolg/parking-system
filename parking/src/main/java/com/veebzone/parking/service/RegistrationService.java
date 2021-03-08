package com.veebzone.parking.service;

import com.veebzone.parking.dto.RegistrationDto;
import com.veebzone.parking.exception.NoSlotsLeftException;
import com.veebzone.parking.exception.NotFoundException;
import com.veebzone.parking.model.Registration;
import com.veebzone.parking.model.Slot;
import com.veebzone.parking.repository.CustomerRepository;
import com.veebzone.parking.repository.RegistrationRepository;
import com.veebzone.parking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    @Autowired
    RegistrationRepository registrationRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    SlotAssignmentService slotAssignmentService;
    @Autowired
    PricingService pricingService;

    public List<RegistrationDto> getAllRegistrations(boolean active) {
        List<Registration> registrations;
        if (active) {
            registrations =  registrationRepository.findActiveRegistrations();
        } else {
            registrations =  registrationRepository.findAll();
        }

        List <RegistrationDto> registrationDtos = registrations
                .stream().map(registration -> {
                    return mapToDto(registration);
                })
                .collect(Collectors.toList());

        return registrationDtos;
    }

    public void insertRegistration(RegistrationDto registrationDto) {
        Registration registration = mapToEntity(registrationDto);
        Slot assignedSlot = slotAssignmentService.findCompatibleSlot(registration);
        if (assignedSlot == null) {
            throw new NoSlotsLeftException();
        }
        double pricePerMinute = pricingService.calculatePricePerMinute(registration);

        registration.setSlot(assignedSlot);
        registration.setPrice(pricePerMinute);
        registrationRepository.save(registration);
    }

    public RegistrationDto getSingleRegistration(Long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(NotFoundException::new);
        return mapToDto(registration);
    }

    public void deleteSingleRegistration(Long id) {
        registrationRepository.deleteById(id);
    }

    public RegistrationDto mapToDto(Registration registration) {
        RegistrationDto registrationDto = new RegistrationDto();

        registrationDto.setId(registration.getId());
        registrationDto.setCheckinTime(registration.getCheckinTime());
        registrationDto.setCheckoutTime(registration.getCheckoutTime());
        registrationDto.setPrice(registration.getPrice());
        registrationDto.setNotes(registration.getNotes());
        registrationDto.setCustomer(registration.getCustomer().getId());
        registrationDto.setVehicle(registration.getVehicle().getId());
        registrationDto.setSlot(registration.getSlot().getId());
        return registrationDto;
    }

    public Registration mapToEntity(RegistrationDto registrationDto) {
        Registration registration = new Registration();
        registration.setCheckinTime(registrationDto.getCheckinTime());
        registration.setCheckoutTime(registrationDto.getCheckoutTime());
        registration.setNotes(registrationDto.getNotes());
        registration.setCustomer(customerRepository.findById(registrationDto.getCustomer()).orElseThrow(NotFoundException::new));
        registration.setVehicle(vehicleRepository.findById(registrationDto.getVehicle()).orElseThrow(NotFoundException::new));
        return registration;
    }

}
