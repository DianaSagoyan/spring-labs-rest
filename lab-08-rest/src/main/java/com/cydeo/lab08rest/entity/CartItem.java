package com.cydeo.lab08rest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class CartItem extends BaseEntity {
    @ManyToOne
    private Product product;
    private Integer quantity;
    @ManyToOne
    private Cart cart;
}
