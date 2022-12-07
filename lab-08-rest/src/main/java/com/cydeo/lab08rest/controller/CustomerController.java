package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.CustomerDTO;
import com.cydeo.lab08rest.model.ResponseWrapper;
import com.cydeo.lab08rest.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getCustomerList(){
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved all customers", customerService.getCustomerList(), HttpStatus.OK));
    }

    @GetMapping("{email}")
    public ResponseEntity<ResponseWrapper> getCustomerListByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved customers by email", customerService.getCustomerListByEmail(email), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createCustomer(@RequestBody CustomerDTO customer){
        return ResponseEntity.ok(new ResponseWrapper("Customer created", customerService.createCustomer(customer), HttpStatus.OK));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateCustomer(@RequestBody CustomerDTO customer){
        return ResponseEntity.ok(new ResponseWrapper("Customer updated", customerService.updateCustomer(customer), HttpStatus.OK));
    }
}
