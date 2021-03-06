package com.veebzone.parking.service;

import com.veebzone.parking.dto.RegistrationDto;
import com.veebzone.parking.model.Registration;
import com.veebzone.parking.repository.CustomerRepository;
import com.veebzone.parking.repository.RegistrationRepository;
import com.veebzone.parking.repository.SlotRepository;
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
    SlotRepository slotRepository;

    public List<RegistrationDto> getAllRegistrations() {
        List<Registration> registrations =  registrationRepository.findAll();

        List <RegistrationDto> registrationDtos = registrations
                .stream().map(registration -> {
                    return mapToDto(registration);
                })
                .collect(Collectors.toList());

        return registrationDtos;
    }

    public void insertRegistration(RegistrationDto registrationDto) {
        registrationRepository.save(mapToEntity(registrationDto));
    }

    public RegistrationDto getSingleRegistration(Long id) {
        Registration registration = registrationRepository.findById(id).get();
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
        registration.setCustomer(customerRepository.findById(registrationDto.getCustomer()).get());
        registration.setVehicle(vehicleRepository.findById(registrationDto.getVehicle()).get());
        registration.setSlot(slotRepository.findById(registrationDto.getSlot()).get());
        return registration;
    }
}
