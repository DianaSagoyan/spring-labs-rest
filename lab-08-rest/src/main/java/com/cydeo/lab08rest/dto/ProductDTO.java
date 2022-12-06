package com.cydeo.lab08rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private BigDecimal price;
    private Integer quantity;
    private Integer remainingQuantity;
    private String name;

}
