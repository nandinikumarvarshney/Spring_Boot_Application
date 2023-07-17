package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name="user_table")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int userid;

    @Column
    private String name;

    @Column
    private int TTL;

    @Column
    private Timestamp timestamp;


}
