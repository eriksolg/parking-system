package com.veebzone.parking.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Table(name = "slot")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "slot_id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "floor_id")
    private Floor floor;


    @OneToMany(mappedBy = "slot")
    private Set<Registration> registrations;
}
