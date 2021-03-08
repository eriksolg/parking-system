package com.veebzone.parking.service;

import com.veebzone.parking.exception.NotFoundException;
import com.veebzone.parking.model.Customer;
import com.veebzone.parking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Customer getSingleCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    public void deleteSingleCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}