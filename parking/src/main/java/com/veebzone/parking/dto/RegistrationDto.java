package com.veebzone.parking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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

    @JsonProperty("pricePerMinute")
    private double price;

    private Timestamp checkinTime;
    private Timestamp checkoutTime;
    private String notes;
}
