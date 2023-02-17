package com.ajulibe.java.SpringBootApi.service;

import com.ajulibe.java.SpringBootApi.entity.Contact;

import com.ajulibe.java.SpringBootApi.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> findById(Long userId) {
        return contactRepository.findByUserId(userId);
    }

    public void save(Contact contact) {
        contactRepository.saveUserContactDetails(contact);
    }


}
