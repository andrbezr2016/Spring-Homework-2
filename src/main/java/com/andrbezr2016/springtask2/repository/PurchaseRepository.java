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

    @Query(value = "select p.p_id, c.c_name, s.s_name from purchase p join customer c on (c.c_id = p.p_customer) join store s on (s.s_id = p.p_store)", nativeQuery = true)
    List<Object[]> findCustomersAndStores();

    @Query(value = "select p.p_id, p.p_date, c.c_name, c.c_sale, b.b_title, p.p_quantity from purchase p join customer c on (c.c_id = p.p_customer) join book b on (b.b_id = p.p_book)", nativeQuery = true)
    List<Object[]> findCustomersAndBooks();

    @Query(value = "select p.p_id, c.c_name, p.p_date from purchase p join customer c on (c.c_id = p.p_customer) where p.p_sum >= 60000", nativeQuery = true)
    List<Object[]> findSumGreaterOrEqualThan60000();

    @Query(value = "select p.p_id, c.c_name, c.c_residence, p.p_date from purchase p join customer c on (c.c_id = p.p_customer) join store s on (s.s_id = p.p_store and s.s_location = c.c_residence) where p.p_date >= cast('2021-03-01' as date) order by p.p_date", nativeQuery = true)
    List<Object[]> findInTheSameDistrict();
}
