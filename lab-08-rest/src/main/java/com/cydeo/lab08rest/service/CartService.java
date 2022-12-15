package com.cydeo.lab08rest.service;


import com.cydeo.lab08rest.dto.CartDTO;

public interface CartService {

    public CartDTO findById(Long id);
}