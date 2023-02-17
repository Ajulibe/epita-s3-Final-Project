package com.ajulibe.java.SpringBootApi.controller;

import com.ajulibe.java.SpringBootApi.dto.ContactDetailsDTO;
import com.ajulibe.java.SpringBootApi.entity.Address;
import com.ajulibe.java.SpringBootApi.entity.Contact;
import com.ajulibe.java.SpringBootApi.entity.UserEntity;
import com.ajulibe.java.SpringBootApi.repository.AddressRepository;
import com.ajulibe.java.SpringBootApi.repository.ContactRepository;
import com.ajulibe.java.SpringBootApi.repository.JwtUserRepo;
import com.ajulibe.java.SpringBootApi.security.authentication.helpers.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    private JwtUserRepo userRepository;
    private AddressRepository addressRepository;
    private ContactRepository contactRepository;


    @Autowired
    public AuthenticationController(JwtUserRepo userRepository, AddressRepository  addressRepository, ContactRepository contactRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this. contactRepository = contactRepository;

    }

    @PostMapping("/store-details")
    public ResponseEntity<?> register(@Valid @RequestBody ContactDetailsDTO contactDetails) {
        try {
            // Create a new Contact entity
            Contact contact = new Contact();
            // Set the user
            UserEntity user = new UserEntity(); // set the user here
            contact.setUser(user);
            // Set the contact details
            contact.setFirstName(contactDetails.firstName());
            contact.setLastName(contactDetails.lastName());
            contact.setPhoneNumber(contactDetails.phoneNumber());
            contact.setEmailAddress(contactDetails.emailAddress());

            // Create a new Address entity
            Address address = new Address();
            address.setCountry(contactDetails.country());
            address.setArea(contactDetails.area());
            address.setCity(contactDetails.city());
            address.setStreet(contactDetails.street());
            address.setStreetNumber(contactDetails.streetNumber());

            // Save the Address entity
            addressRepository.save(address);
            // Set the billing address of the Contact entity
            contact.setBillingAddress(address);
            // Save the Contact entity
            contactRepository.saveUserContactDetails(contact);

            return ResponseEntity.ok("Details saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save details");
        }
    }
}
