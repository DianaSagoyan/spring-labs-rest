package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.AddressDTO;
import com.cydeo.lab08rest.entity.Customer;
import com.cydeo.lab08rest.model.ResponseWrapper;
import com.cydeo.lab08rest.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAddressList(){
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved addresses", addressService.getAddressList(), HttpStatus.OK));
    }

    @GetMapping("/startsWith/{address}")
    public ResponseEntity<ResponseWrapper> getAddressListByStartsWithAddress(@PathVariable("address") String address){
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved addresses starting with", addressService.getAddressListByStartsWithAddress(address), HttpStatus.OK));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<ResponseWrapper> getAddressListByCustomerId(@PathVariable("id") Long id){
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved addresses with customer id", addressService.getAddressListByCustomerId(id), HttpStatus.OK));
    }

    @GetMapping("/customer/{customerId}/name/{name}")
    public ResponseEntity<ResponseWrapper> getAddressListByCustomerAndName(@PathVariable("customerId") Customer id, @PathVariable("name") String name){
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved addresses with Customer and name", addressService.getAddressListByCustomerAndName(id, name), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createAddress(@RequestBody AddressDTO address){
        return ResponseEntity.ok(new ResponseWrapper("Address successfully created", addressService.create(address), HttpStatus.CREATED));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateAddress(@RequestBody AddressDTO address){

        return ResponseEntity.ok(new ResponseWrapper("Address successfully updated", addressService.updateAddress(address), HttpStatus.CREATED));
    }

}
