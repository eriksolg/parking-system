package com.veebzone.parking.service;

import com.veebzone.parking.dto.RegistrationDto;
import com.veebzone.parking.exception.AlreadyRegisteredException;
import com.veebzone.parking.exception.NoSlotsLeftException;
import com.veebzone.parking.exception.NotFoundException;
import com.veebzone.parking.model.Registration;
import com.veebzone.parking.model.Slot;
import com.veebzone.parking.repository.RegistrationRepository;
import com.veebzone.parking.service.mappers.RegistrationDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

        return registrations
                .stream().map(registration -> registrationDtoMapper.mapRegistrationToDto(registration))
                .collect(Collectors.toList());
    }

    public RegistrationDto insertRegistration(RegistrationDto registrationDto) {
        Registration registration = registrationDtoMapper.mapRegistrationDtoToEntity(registrationDto);
        Slot assignedSlot = slotAssignmentService.findCompatibleSlot(registration);
        if (assignedSlot == null) {
            throw new NoSlotsLeftException();
        }
        double pricePerMinute = pricingService.calculatePricePerMinute(registration);

        List <Registration> activeRegistrations = registrationRepository.findActiveRegistrations();
        for (Registration activeRegistration : activeRegistrations) {
            if (activeRegistration.getVehicle().getId().equals(registrationDto.getVehicle()) ||
                activeRegistration.getCustomer().getId().equals(registrationDto.getCustomer())) {
                throw new AlreadyRegisteredException();
            }
        }

        registration.setSlot(assignedSlot);
        registration.setPrice(pricePerMinute);
        registration.setCheckinTime(new Timestamp(System.currentTimeMillis()));
        Registration savedRegistration = registrationRepository.save(registration);

        return registrationDtoMapper.mapRegistrationToDto(savedRegistration);
    }

    public RegistrationDto getSingleRegistration(Long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(NotFoundException::new);

        return registrationDtoMapper.mapRegistrationToDto(registration);
    }

    public void deleteSingleRegistration(Long id) {
        try {
            registrationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException();
        }
    }

    public RegistrationDto patchRegistrationCheckoutTime(Long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(NotFoundException::new);
        registration.setCheckoutTime(new Timestamp(System.currentTimeMillis()));
        registrationRepository.save(registration);

        return registrationDtoMapper.mapRegistrationToDto(registration);
    }
}
