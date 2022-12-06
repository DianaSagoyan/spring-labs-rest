package com.cydeo.lab08rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductRequest {
    private List<Long> categoryList;
    private BigDecimal price;
}
