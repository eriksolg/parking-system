package com.veebzone.parking.controller;

import com.veebzone.parking.model.Customer;
import com.veebzone.parking.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "/api/customers")
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @ApiOperation(value="Get Customers")
    @GetMapping("/api/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @ApiOperation(value="Create Customer")
    @PostMapping("/api/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer insertCustomer(@RequestBody Customer customer) { return customerService.insertCustomer(customer);}

    @ApiOperation(value="Get Single Customer")
    @GetMapping("/api/customers/{id}")
    public Customer getSingleCustomer(@PathVariable Long id) {
        return customerService.getSingleCustomer(id);
    }

    @ApiOperation(value="Delete Customer")
    @DeleteMapping("/api/customers/{id}")
    public void deleteSingletomer(@PathVariable Long id) {
        customerService.deleteSingleCustomer(id);
    }
}
