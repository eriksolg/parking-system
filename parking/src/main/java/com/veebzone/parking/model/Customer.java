package com.veebzone.parking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "customer_id")
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<Registration> registrations;

    @NotNull
    @Column(name = "id_number", unique = true)
    private String idNumber;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;
}
