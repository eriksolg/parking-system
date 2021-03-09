package com.veebzone.parking.service;

import com.veebzone.parking.exception.DuplicateValueException;
import com.veebzone.parking.exception.InUseException;
import com.veebzone.parking.exception.NotFoundException;
import com.veebzone.parking.model.Customer;
import com.veebzone.parking.repository.CustomerRepository;
import com.veebzone.parking.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RegistrationRepository registrationRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer insertCustomer(Customer customer) {
        if (customerRepository.findByIdNumber(customer.getIdNumber()).isPresent())
            throw new DuplicateValueException();

        return customerRepository.save(customer);
    }

    public Customer getSingleCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void deleteSingleCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(NotFoundException::new);
        if (registrationRepository.findRegistrationsByCustomer(customer).size() != 0)
            throw new InUseException();

        customerRepository.deleteById(id);
    }
}