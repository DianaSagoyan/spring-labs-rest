package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.dto.PaymentDTO;
import com.cydeo.lab08rest.entity.Payment;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.PaymentRepository;
import com.cydeo.lab08rest.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final MapperUtil mapperUtil;

    public PaymentServiceImpl(PaymentRepository paymentRepository, MapperUtil mapperUtil) {
        this.paymentRepository = paymentRepository;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public PaymentDTO findById(Long id) {
        return mapperUtil.convert(paymentRepository.findById(id), new PaymentDTO());
    }
}