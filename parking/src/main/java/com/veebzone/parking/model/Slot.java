package com.veebzone.parking.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@RestResource(exported = false)
@Table(name = "slot")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "slot_id")
    private Long id;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @NotNull
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @JsonIgnore
    @OneToMany(mappedBy = "slot")
    private Set<Registration> registrations;

    @NotNull
    @Column(name = "name")
    private String name;



}
