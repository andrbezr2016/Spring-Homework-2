package com.andrbezr2016.springtask2.repository;

import com.andrbezr2016.springtask2.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Query(value = "select s_name from store where s_location = :district", nativeQuery = true)
    List<String> findByLocation(@Param("district") String district);

    @Query(value = "select distinct s.s_name from store s join purchase p on (s.s_id = p.p_store and s.s_location <> 'Avtozavodsky') join customer c on (c.c_id = p.p_customer and c.c_sale between 10 and 15)", nativeQuery = true)
    List<String> findCustomersWithSaleBetween10And15();
}
