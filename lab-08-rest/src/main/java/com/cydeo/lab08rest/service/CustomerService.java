package com.cydeo.lab08rest.service;

import com.cydeo.lab08rest.dto.CustomerDTO;
import com.cydeo.lab08rest.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getCustomerList();
    CustomerDTO createCustomer(CustomerDTO customer);
    CustomerDTO updateCustomer(CustomerDTO customer);

    List<CustomerDTO> getCustomerListByEmail(String email);
}
