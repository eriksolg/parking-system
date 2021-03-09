package com.veebzone.parking.repository;

import com.veebzone.parking.model.Customer;
import com.veebzone.parking.model.Registration;
import com.veebzone.parking.model.Slot;
import com.veebzone.parking.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @Query("SELECT r FROM Registration r WHERE r.checkinTime < CURRENT_TIMESTAMP AND (r.checkoutTime > CURRENT_TIMESTAMP OR r.checkoutTime is null)")
    List<Registration> findActiveRegistrations();

    @Query("SELECT Count(r) FROM Registration r WHERE r.checkinTime < CURRENT_TIMESTAMP AND (r.checkoutTime > CURRENT_TIMESTAMP OR r.checkoutTime is null)")
    int getNumberOfActiveRegistrations();

    @Query("SELECT r FROM Registration r WHERE r.customer = :customer")
    List<Registration> findRegistrationsByCustomer(Customer customer);

    @Query("SELECT r FROM Registration r WHERE r.slot = :slot")
    List<Registration> findRegistrationsBySlot(Slot slot);

    @Query("SELECT r FROM Registration r WHERE r.vehicle = :vehicle")
    List<Registration> findRegistrationsByVehicle(Vehicle vehicle);}
