package com.andrbezr2016.springtask2.repository;

import com.andrbezr2016.springtask2.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "select distinct b_title, b_price from book", nativeQuery = true)
    List<Object[]> findTitleAndPrices();

    @Query(value = "select b_title, b_price from book where (b_title like %:word%) or (b_price > :price) order by b_title asc, b_price desc", nativeQuery = true)
    List<Object[]> findWhere(@Param("word") String word, @Param("price") Integer price);
}
