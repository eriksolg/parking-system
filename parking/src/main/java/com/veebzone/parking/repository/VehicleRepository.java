package com.veebzone.parking.repository;

import com.veebzone.parking.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v WHERE v.registrationNumber = :registrationNumber")
    Vehicle findByRegNumber(String registrationNumber);
}