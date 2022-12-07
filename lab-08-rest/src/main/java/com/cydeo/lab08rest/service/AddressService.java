package com.cydeo.lab08rest.service;

import com.cydeo.lab08rest.dto.AddressDTO;
import com.cydeo.lab08rest.entity.Customer;

import java.util.List;

public interface AddressService {
    List<AddressDTO> getAddressList();
    List<AddressDTO> getAddressListByStartsWithAddress(String address);
    List<AddressDTO> getAddressListByCustomerId(Long id);
    List<AddressDTO> getAddressListByCustomerAndName(Customer customer, String name);
    AddressDTO create(AddressDTO address);
    AddressDTO updateAddress(AddressDTO address);
}
