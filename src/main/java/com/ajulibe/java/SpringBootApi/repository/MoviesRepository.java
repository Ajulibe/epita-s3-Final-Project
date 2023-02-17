package com.ajulibe.java.SpringBootApi.repository;


import com.ajulibe.java.SpringBootApi.entity.Movie;
import com.ajulibe.java.SpringBootApi.interfaces.MoviesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Repository
public class MoviesRepository implements MoviesInterface  {
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public MoviesRepository(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }


    @Override
    @Transactional
    public List<Movie> findAll() {
        TypedQuery<Movie> theQuery = entityManager.createQuery("SELECT m FROM Movie m", Movie.class);
        List<Movie> movieList = theQuery.getResultList();
        return movieList;
    }

    @Override
    @Transactional
    public void save(List<Movie> movies) {
        for (Movie movie : movies) {
            entityManager.persist(movie);
        }
    }

    @Transactional
    public List<Movie> getRecentMovie() {
            Date fourMonthsAgo = new Date(System.currentTimeMillis() - (long) 4 * 30 * 24 * 60 * 60 * 1000);
            Query query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.releaseDate >= :fourMonthsAgo");
            query.setParameter("fourMonthsAgo", fourMonthsAgo);
            return query.getResultList();
    }


    public List<Movie> getRecommendedMovies() {
        String jpql = "SELECT m FROM Movie m WHERE m.recommended = 'true'";
        TypedQuery<Movie> query = entityManager.createQuery(jpql, Movie.class);
        return query.getResultList();
    }

}
