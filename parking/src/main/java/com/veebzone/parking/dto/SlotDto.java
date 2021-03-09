package com.veebzone.parking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SlotDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private long floor;

    private String name;
}
