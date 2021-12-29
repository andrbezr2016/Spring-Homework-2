package com.andrbezr2016.springtask2.repository;

import com.andrbezr2016.springtask2.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
}
