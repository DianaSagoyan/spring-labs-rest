package com.cydeo.lab08rest.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomerDTO{
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
}
