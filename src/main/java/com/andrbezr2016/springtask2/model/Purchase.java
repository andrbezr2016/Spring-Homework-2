package com.andrbezr2016.springtask2.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "purchase")
@Data
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id", nullable = false)
    private int id;

    @Column(name = "p_date", nullable = false)
    private Date date;

    @Column(name = "p_store", nullable = false)
    private int store;

    @Column(name = "p_customer", nullable = false)
    private int customer;

    @Column(name = "p_book", nullable = false)
    private int book;

    @Column(name = "p_quantity", nullable = false)
    private int quantity;

    @Column(name = "p_sum", nullable = false)
    private int sum;
}
