package com.veebzone.parking.controller;

import com.veebzone.parking.dto.RegistrationDto;
import com.veebzone.parking.service.RegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "/api/registrations")
@RestController
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    @ApiOperation(value="Get Registrations")
    @GetMapping("/api/registrations")
    public List<RegistrationDto> getAllRegistrations(
            @ApiParam(value="Get only active registrations", name="active")
            @RequestParam(name = "active", defaultValue = "false" ) boolean active) {
        return registrationService.getAllRegistrations(active);
    }

    @ApiOperation(value="Create Registration")
    @PostMapping("/api/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationDto insertRegistration(@RequestBody RegistrationDto registration) {
        return registrationService.insertRegistration(registration);
    }

    @ApiOperation(value="Get Single Registration")
    @GetMapping("/api/registrations/{id}")
    public RegistrationDto getSingleRegistration(@PathVariable Long id) {
        return registrationService.getSingleRegistration(id);
    }

    @ApiOperation(value="Patch Registration (Checkout)")
    @PatchMapping("/api/registrations/{id}")
    public RegistrationDto patchRegistrationCheckoutTime(@PathVariable Long id) {
        return registrationService.patchRegistrationCheckoutTime(id);
    }

    @ApiOperation(value="Delete Registration")
    @DeleteMapping("/api/registrations/{id}")
    public void deleteSingleRegistration(@PathVariable Long id) {
        registrationService.deleteSingleRegistration(id);
    }
}
