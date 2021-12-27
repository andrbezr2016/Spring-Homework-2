package com.andrbezr2016.springtask2.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id", nullable = false)
    private int id;

    @Column(name = "c_name", nullable = false)
    private String name;

    @Column(name = "c_residence", nullable = false)
    private String residence;

    @Column(name = "c_sale", nullable = false)
    private int sale;
}
