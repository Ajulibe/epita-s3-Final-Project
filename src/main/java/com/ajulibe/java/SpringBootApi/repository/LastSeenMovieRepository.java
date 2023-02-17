package com.ajulibe.java.SpringBootApi.repository;

import com.ajulibe.java.SpringBootApi.entity.LastSeenMovie;
import com.ajulibe.java.SpringBootApi.interfaces.LastSeenMovieInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class LastSeenMovieRepository implements LastSeenMovieInterface {

    private EntityManager entityManager;

    @Autowired
    public LastSeenMovieRepository(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<LastSeenMovie> findByUserId(String userId) {
        String jpql = "SELECT l FROM LastSeenMovie l WHERE l.userId = :userId";
        TypedQuery<LastSeenMovie> query = entityManager.createQuery(jpql, LastSeenMovie.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Transactional
    public void saveLastSeenMovie(LastSeenMovie lastSeenMovie) {
        entityManager.merge(lastSeenMovie);
    }

    @Transactional
    public void deleteLastSeenMovie(LastSeenMovie lastSeenMovie) {
        entityManager.remove(lastSeenMovie);
    }

}