package com.cydeo.lab08rest.service;

import com.cydeo.lab08rest.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO findById(Long customerId);
    List<CustomerDTO> readAll();
    CustomerDTO update(CustomerDTO customerDTO);
    CustomerDTO create(CustomerDTO customerDTO);
    CustomerDTO readByEmail(String email);
}
