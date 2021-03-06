package com.veebzone.parking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.veebzone.parking.model.Floor;
import lombok.Data;

@Data
public class SlotDto {
    Long id;
    private String name;
}
