package com.veebzone.parking.service;

import com.veebzone.parking.model.Customer;
import com.veebzone.parking.model.Vehicle;
import com.veebzone.parking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void insertCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Optional<Customer> getSingleCustomer(Long id) {
        return customerRepository.findById(id);
    }

    public void deleteSingleCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}