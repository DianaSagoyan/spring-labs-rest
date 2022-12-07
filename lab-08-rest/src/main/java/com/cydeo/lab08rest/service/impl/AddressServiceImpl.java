package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.dto.AddressDTO;
import com.cydeo.lab08rest.entity.Address;
import com.cydeo.lab08rest.entity.Customer;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.AddressRepository;
import com.cydeo.lab08rest.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final MapperUtil mapperUtil;

    public AddressServiceImpl(AddressRepository addressRepository, MapperUtil mapperUtil) {
        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<AddressDTO> getAddressList() {
        return addressRepository.findAll().stream().map(address -> mapperUtil.convert(address,  new AddressDTO())).collect(Collectors.toList());
    }

    @Override
    public List<AddressDTO> getAddressListByStartsWithAddress(String address) {
        return addressRepository.findAllByStreetStartingWith(address).stream().map(address1 -> mapperUtil.convert(address1, new AddressDTO())).collect(Collectors.toList());
    }

    @Override
    public List<AddressDTO> getAddressListByCustomerId(Long id) {
        return addressRepository.retrieveByCustomerId(id).stream().map(address -> mapperUtil.convert(address, new AddressDTO())).collect(Collectors.toList());
    }

    @Override
    public List<AddressDTO> getAddressListByCustomerAndName(Customer customer, String name) {
        return addressRepository.findAllByCustomerAndName(customer, name).stream().map(address -> mapperUtil.convert(address, new AddressDTO())).collect(Collectors.toList());
    }

    @Override
    public AddressDTO create(AddressDTO address) {
        Address createdAddress = mapperUtil.convert(address, new Address());
        addressRepository.save(createdAddress);
        return address;
    }

    @Override
    public AddressDTO updateAddress(AddressDTO addressDTO) {
        Address address = addressRepository.findById(addressDTO.getId());
        // ???
        return addressDTO;
    }


}
