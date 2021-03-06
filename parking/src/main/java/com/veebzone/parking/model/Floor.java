package com.veebzone.parking.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

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

    @JsonIgnore
    @OneToMany (mappedBy = "floor")
    private Set<Slot> slots;

    @NotNull
    @Column(name = "floor_number")
    private String floorNumber;

    @NotNull
    @Column(name = "height")
    private int height;

    @NotNull
    @Column(name = "weight_cap")
    private int weightCapacity;
}
