package com.cydeo.lab08rest.service;

import com.cydeo.lab08rest.dto.PaymentDTO;
import com.cydeo.lab08rest.entity.Payment;

public interface PaymentService {

    PaymentDTO findById(Long id);

}