package com.veebzone.parking.repository;

import com.veebzone.parking.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @Query("SELECT r FROM Registration r WHERE r.checkinTime < CURRENT_TIMESTAMP AND (r.checkoutTime > CURRENT_TIMESTAMP OR r.checkoutTime is null)")
    List<Registration> findActiveRegistrations();
}
