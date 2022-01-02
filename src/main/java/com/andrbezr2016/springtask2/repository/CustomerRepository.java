package com.andrbezr2016.springtask2.repository;

import com.andrbezr2016.springtask2.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "select distinct c_residence from customer", nativeQuery = true)
    List<String> findDistricts();

    @Query(value = "select c_name, c_sale from customer where c_residence = :district", nativeQuery = true)
    List<Object[]> findByResidence(@Param("district") String district);
}
