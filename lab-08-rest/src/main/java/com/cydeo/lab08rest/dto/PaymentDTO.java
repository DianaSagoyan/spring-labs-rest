package com.cydeo.lab08rest.dto;

import com.cydeo.lab08rest.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDTO {
    private BigDecimal paidPrice;
    private PaymentMethod paymentMethod;
}
