package com.cydeo.lab08rest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Category extends BaseEntity {
    private String name;
}
