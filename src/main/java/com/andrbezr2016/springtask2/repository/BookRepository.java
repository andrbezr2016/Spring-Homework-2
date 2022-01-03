package com.andrbezr2016.springtask2.repository;

import com.andrbezr2016.springtask2.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "select distinct b_title, b_price from book", nativeQuery = true)
    List<Object[]> findTitleAndPrices();

    @Query(value = "select b_title, b_price from book where (b_title like %'Windows'%) or (b_price > 20000) order by b_title asc, b_price desc", nativeQuery = true)
    List<Object[]> findWindowsOr20000();

    @Query(value = "select b_title, b_storage, b_quantity, b_price from book b join purchase p on (b.b_id = p.p_book and b.b_quantity > 10) join store s on (s.s_id = p.p_store and s.s_location = b.b_storage) order by b_price asc", nativeQuery = true)
    List<Object[]> findPurchasedInfo();
}
