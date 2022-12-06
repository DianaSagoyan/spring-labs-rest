package com.cydeo.lab08rest.repository;

import com.cydeo.lab08rest.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    //Write a derived query to get category by name
    Category findByName(String name);

    //Write a derived query to get top 3 categories order by name desc
    List<Category> findTop3ByOrderByNameDesc();

}
