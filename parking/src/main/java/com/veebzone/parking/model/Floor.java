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
@Table(name = "floor")
public class Floor {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "floor_id")
    private Long id;

    @JsonIgnore
    @OneToMany (mappedBy = "floor")
    private Set<Slot> slots;

    @NotNull
    @Column(name = "floor_number", unique = true)
    private String floorNumber;

    @NotNull
    @Column(name = "height")
    private int height;

    @NotNull
    @Column(name = "weight_cap")
    private int weightCapacity;
}
