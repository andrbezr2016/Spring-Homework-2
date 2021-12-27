package com.andrbezr2016.springtask2.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "book")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_id", nullable = false)
    private int id;

    @Column(name = "b_title", nullable = false)
    private String title;

    @Column(name = "b_price", nullable = false)
    private double price;

    @Column(name = "b_storage", nullable = false)
    private String storage;

    @Column(name = "b_quantity", nullable = false)
    private int quantity;
}
