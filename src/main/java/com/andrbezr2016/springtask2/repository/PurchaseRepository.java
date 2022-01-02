package com.andrbezr2016.springtask2.repository;

import com.andrbezr2016.springtask2.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    @Query(value = "select distinct to_char(p_date, 'Month') from purchase", nativeQuery = true)
    List<String> findMonths();
}
