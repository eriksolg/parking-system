package com.veebzone.parking.repository;

import com.veebzone.parking.model.Floor;
import com.veebzone.parking.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

    @Query("SELECT s FROM Slot s WHERE s.floor = :floor")
    List<Slot> findByFloor(Floor floor);

    @Query("SELECT s FROM Slot s WHERE NOT EXISTS ( SELECT r FROM Registration r WHERE r.checkinTime < CURRENT_TIMESTAMP AND (r.checkoutTime > CURRENT_TIMESTAMP OR r.checkoutTime is null) AND r.slot = s)")
    List<Slot> findAvailableSlots();

    @Query("SELECT COUNT(s) FROM Slot s")
    int getNumberOfSlots();
}
