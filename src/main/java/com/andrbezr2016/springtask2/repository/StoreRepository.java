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
}
