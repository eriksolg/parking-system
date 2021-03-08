package com.veebzone.parking.service;

import com.veebzone.parking.dto.RegistrationDto;
import com.veebzone.parking.exception.NoSlotsLeftException;
import com.veebzone.parking.exception.NotFoundException;
import com.veebzone.parking.model.Registration;
import com.veebzone.parking.model.Slot;
import com.veebzone.parking.repository.RegistrationRepository;
import com.veebzone.parking.service.mappers.RegistrationDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    @Autowired
    RegistrationRepository registrationRepository;
    @Autowired
    RegistrationDtoMapper registrationDtoMapper;

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
                    return registrationDtoMapper.mapRegistrationToDto(registration);
                })
                .collect(Collectors.toList());

        return registrationDtos;
    }

    public void insertRegistration(RegistrationDto registrationDto) {
        Registration registration = registrationDtoMapper.mapRegistrationDtoToEntity(registrationDto);
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
        return registrationDtoMapper.mapRegistrationToDto(registration);
    }

    public void deleteSingleRegistration(Long id) {
        registrationRepository.deleteById(id);
    }



}
