package com.cydeo.lab08rest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Customer extends BaseEntity {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<Address> addressList;
}
