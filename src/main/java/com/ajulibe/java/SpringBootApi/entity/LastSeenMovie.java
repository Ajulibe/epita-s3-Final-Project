package com.ajulibe.java.SpringBootApi.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "last_seen_movies")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LastSeenMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_seen_date")
    private Date lastSeenDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LastSeenMovie that = (LastSeenMovie) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

