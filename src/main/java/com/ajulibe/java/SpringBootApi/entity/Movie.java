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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Movie movie = (Movie) o;
        return id != null && Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

