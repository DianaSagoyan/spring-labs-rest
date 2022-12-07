package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.dto.CustomerDTO;
import com.cydeo.lab08rest.entity.Customer;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.CustomerRepository;
import com.cydeo.lab08rest.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final MapperUtil mapperUtil;

    public CustomerServiceImpl(CustomerRepository customerRepository, MapperUtil mapperUtil) {
        this.customerRepository = customerRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CustomerDTO> getCustomerList() {
        return customerRepository.findAll().stream().map(customer -> mapperUtil.convert(customer, new CustomerDTO())).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customer) {
       customerRepository.save(mapperUtil.convert(customer, new Customer()));
       return customer;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        //??
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> getCustomerListByEmail(String email) {
        return customerRepository.findByEmail(email).stream().map(customer -> mapperUtil.convert(customer, new CustomerDTO())).collect(Collectors.toList());
    }
}
