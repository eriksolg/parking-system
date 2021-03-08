package com.veebzone.parking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@RestResource(exported = false)
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "vehicle_id")
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "vehicle")
    private Set<Registration> registrations;

    @NotNull
    @Column(name = "reg_number", unique = true)
    private String registrationNumber;

    @NotNull
    @Column(name = "weight")
    private int weight;

    @NotNull
    @Column(name = "height")
    private int height;
}
