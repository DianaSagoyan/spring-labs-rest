package com.cydeo.lab08rest.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Balance extends BaseEntity{
    @OneToOne
    private Customer customer;
    private BigDecimal amount;
}
