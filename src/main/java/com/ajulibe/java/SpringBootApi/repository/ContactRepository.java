package com.ajulibe.java.SpringBootApi.repository;

import com.ajulibe.java.SpringBootApi.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ContactRepository {

    private EntityManager entityManager;

    @Autowired
    public ContactRepository(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Transactional
    public List<Contact> findByUserId(Long userId) {
        String jpql = "SELECT c FROM Contact c WHERE c.user.id = :userId";
        TypedQuery<Contact> query = entityManager.createQuery(jpql, Contact.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Transactional
    public void saveUserContactDetails(Contact contact) {
        entityManager.persist(contact);
    }

}