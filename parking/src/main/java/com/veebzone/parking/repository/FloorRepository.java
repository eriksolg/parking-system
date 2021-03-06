package com.veebzone.parking.repository;

import com.veebzone.parking.model.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
    @Query("SELECT f FROM Floor f WHERE f.id = :id")
    Optional<Floor> findFloorByFloorNumber(Long id);
}