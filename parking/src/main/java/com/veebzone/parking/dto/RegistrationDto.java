package com.veebzone.parking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class RegistrationDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty("vehicleId")
    private Long vehicle;

    @JsonProperty("customerId")
    private Long customer;

    @JsonProperty(value = "slotId", access = JsonProperty.Access.READ_ONLY)
    private Long slot;

    @JsonProperty(value = "pricePerMinute", access = JsonProperty.Access.READ_ONLY)
    private double price;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp checkinTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp checkoutTime;

    private String notes;
}
