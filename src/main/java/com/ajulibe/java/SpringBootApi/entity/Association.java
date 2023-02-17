package com.ajulibe.java.SpringBootApi.entity;

import javax.persistence.*;

@Entity
@Table(name = "association")
public class Association {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "association_id")
    private int association_id;

    @Column(name = "user_id")
    private int user;


    @Column(name = "movie_id")
    private int movie_id;
}
