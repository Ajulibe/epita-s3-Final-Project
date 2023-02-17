package com.ajulibe.java.SpringBootApi.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@Data
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String area;

    private String city;

    private String street;

    private String streetNumber;

    @OneToOne(mappedBy = "billingAddress")
    private Contact contact;


}
