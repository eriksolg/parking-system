package com.veebzone.parking.repository;


import com.veebzone.parking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.idNumber = :idNumber")
    Optional<Customer> findByIdNumber(String idNumber);
}
