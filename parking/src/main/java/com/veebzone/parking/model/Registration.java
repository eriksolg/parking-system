package com.veebzone.parking.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@RestResource(exported = false)
@Table(name = "registration")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "registration_id")
    private Long id;

    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    //@JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @NotNull
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @NotNull
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @NotNull
    @JoinColumn(name = "slot_id")
    private Slot slot;

    @NotNull
    @Column(name = "checkin_time")
    private Timestamp checkinTime;

    @Column(name = "checkout_time")
    private Timestamp checkoutTime;

    @Column(name = "price")
    private double price;

    @Column(name = "notes")
    private String notes;

}
