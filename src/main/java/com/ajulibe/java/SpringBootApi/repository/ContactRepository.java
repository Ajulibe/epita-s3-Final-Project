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
        System.out.println(contact + "contact details--------------------------------------");
        entityManager.persist(contact);
    }


    @Transactional
    public Contact findByEmailAddress(String emailAddress) {
        String jpql = "SELECT c FROM Contact c WHERE c.emailAddress = :emailAddress";
        TypedQuery<Contact> query = entityManager.createQuery(jpql, Contact.class);
        query.setParameter("emailAddress", emailAddress);
        List<Contact> contacts = query.getResultList();
        if (contacts.isEmpty()) {
            return null;
        } else {
            return contacts.get(0);
        }
    }

}