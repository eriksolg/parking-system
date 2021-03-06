package com.veebzone.parking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
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
