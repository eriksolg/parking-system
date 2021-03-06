package com.veebzone.parking.repository;

import com.veebzone.parking.model.Floor;
import com.veebzone.parking.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

    @Query("SELECT s FROM Slot s WHERE s.floor = :floor")
    List<Slot> findByFloor(Optional<Floor> floor);
}
