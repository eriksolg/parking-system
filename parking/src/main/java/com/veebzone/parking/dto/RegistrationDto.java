package com.veebzone.parking.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.veebzone.parking.model.Customer;
import com.veebzone.parking.model.Slot;
import com.veebzone.parking.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class RegistrationDto {
    private Long id;

    @JsonProperty("vehicleId")
    private Long vehicle;

    @JsonProperty("customerId")
    private Long customer;

    @JsonProperty("slotId")
    private Long slot;

    private Timestamp checkinTime;
    private Timestamp checkoutTime;
    private String notes;
}
