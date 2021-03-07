package com.veebzone.parking.controller;

import com.veebzone.parking.dto.RegistrationDto;
import com.veebzone.parking.model.Registration;
import com.veebzone.parking.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    @GetMapping("/api/registrations")
    public List<RegistrationDto> getAllRegistrations() {
        return registrationService.getAllRegistrations();
    }

    @PostMapping("/api/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertRegistration(@RequestBody RegistrationDto registration) {
        registrationService.insertRegistration(registration);
    }

    @GetMapping("/api/registrations/{id}")
    public RegistrationDto getSingleRegistration(@PathVariable Long id) {
        return registrationService.getSingleRegistration(id);
    }

    @DeleteMapping("/api/registrations/{id}")
    public void deleteSingleRegistration(@PathVariable Long id) {
        registrationService.deleteSingleRegistration(id);
    }
}
