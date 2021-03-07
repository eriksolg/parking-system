package com.veebzone.parking.controller;

import com.veebzone.parking.model.Customer;
import com.veebzone.parking.model.Vehicle;
import com.veebzone.parking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/api/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/api/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void inserCustomer(@RequestBody @Valid Customer customer) {
        customerService.insertCustomer(customer);
    }

    @GetMapping("/api/customers/{id}")
    public Customer getSingleCustomer(@PathVariable Long id) {
        return customerService.getSingleCustomer(id);
    }

    @DeleteMapping("/api/customers/{id}")
    public void deleteSingletomer(@PathVariable Long id) {
        customerService.deleteSingleCustomer(id);
    }
}
