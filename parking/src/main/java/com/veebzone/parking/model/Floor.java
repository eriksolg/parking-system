package com.veebzone.parking.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Table(name = "floor")
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "floor_id")
    private Long id;

    @NotNull
    @Column(name = "height")
    private int height;

    @NotNull
    @Column(name = "weight_cap")
    private int weightCapacity;

    @OneToMany
    private Set<Slot> slots;
}
