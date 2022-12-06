package com.cydeo.lab08rest.repository;

import com.cydeo.lab08rest.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    //Write a derived query to get customer by id
    Optional<Customer> findById(Long id);

    //Write a JPQL query to get customer by email
    @Query("SELECT c FROM Customer  c WHERE c.email = ?1")
    Customer retrieveByCustomerEmail(String email);

}
