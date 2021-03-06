package com.veebzone.parking.model;

import com.fasterxml.jackson.annotation.*;
import com.veebzone.parking.service.ParkingLotService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

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
