package com.andrbezr2016.springtask2.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "store")
@Data
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id", nullable = false)
    private int id;

    @Column(name = "s_title", length = 50, nullable = false)
    private String title;

    @Column(name = "s_location", length = 50, nullable = false)
    private String location;

    @Column(name = "s_commission", nullable = false)
    private int commission;
}
