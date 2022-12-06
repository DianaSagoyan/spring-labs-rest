package com.cydeo.lab08rest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Address extends BaseEntity{
    private String name;
    private String zipCode;
    private String street;
    @ManyToOne
    private Customer customer;
}
