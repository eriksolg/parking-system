package com.veebzone.parking.controller;

import com.veebzone.parking.model.Registration;
import com.veebzone.parking.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RegistrationController {
    @Autowired
    RegistrationRepository registrationRepository;

    @GetMapping("/api/registrations")
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    @PostMapping("/api/registrations")
    public void insertRegistration(@RequestBody @Valid Registration registration) {
        registrationRepository.save(registration);
    }
}
