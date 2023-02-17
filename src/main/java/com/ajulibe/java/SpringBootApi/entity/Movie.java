package com.ajulibe.java.SpringBootApi.entity;

import com.ajulibe.java.SpringBootApi.constants.MovieType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String recommended;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private String poster_path;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @Enumerated(EnumType.STRING)
    private MovieType type;

    private double currentRating = 1.0;
    private int numViewers = 1;

    public Movie(Long id, String title, String recommended, Author author, String poster_path, Date releaseDate, MovieType type) {
    }
}

