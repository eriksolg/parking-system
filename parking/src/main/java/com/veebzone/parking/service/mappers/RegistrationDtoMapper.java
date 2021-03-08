package com.veebzone.parking.service.mappers;

import com.veebzone.parking.dto.RegistrationDto;
import com.veebzone.parking.exception.NotFoundException;
import com.veebzone.parking.model.Registration;
import com.veebzone.parking.repository.CustomerRepository;
import com.veebzone.parking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationDtoMapper {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    VehicleRepository vehicleRepository;

    public RegistrationDto mapRegistrationToDto(Registration registration) {
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

    public Registration mapRegistrationDtoToEntity(RegistrationDto registrationDto) {
        Registration registration = new Registration();
        registration.setCheckinTime(registrationDto.getCheckinTime());
        registration.setCheckoutTime(registrationDto.getCheckoutTime());
        registration.setNotes(registrationDto.getNotes());
        registration.setCustomer(customerRepository.findById(registrationDto.getCustomer()).orElseThrow(NotFoundException::new));
        registration.setVehicle(vehicleRepository.findById(registrationDto.getVehicle()).orElseThrow(NotFoundException::new));
        return registration;
    }
}
