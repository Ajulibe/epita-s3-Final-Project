package com.ajulibe.java.SpringBootApi.controller;

import com.ajulibe.java.SpringBootApi.dto.ContactDetailsDTO;
import com.ajulibe.java.SpringBootApi.entity.Address;
import com.ajulibe.java.SpringBootApi.entity.Contact;
import com.ajulibe.java.SpringBootApi.repository.AddressRepository;
import com.ajulibe.java.SpringBootApi.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    private AddressRepository addressRepository;
    private ContactRepository contactRepository;


    @Autowired
    public AuthenticationController(AddressRepository addressRepository, ContactRepository contactRepository) {
        this.addressRepository = addressRepository;
        this.contactRepository = contactRepository;

    }

    @PostMapping("/store-details")
    public ResponseEntity<?> register(@Valid @RequestBody ContactDetailsDTO contactDetails) {
        try {
            // Check if the contact already exists
            if (contactRepository.findByEmailAddress(contactDetails.emailAddress()) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Contact with the same email address already exists");
            }


            // Create a new Contact entity
            Contact contact = new Contact();
            contact.setUserId(contactDetails.userId() != null ? contactDetails.userId() : null);
            contact.setFirstName(contactDetails.firstName() != null ? contactDetails.firstName() : null);
            contact.setLastName(contactDetails.lastName() != null ? contactDetails.lastName() : null);
            contact.setPhoneNumber(contactDetails.phoneNumber() != null ? contactDetails.phoneNumber() : null);
            contact.setEmailAddress(contactDetails.emailAddress() != null ? contactDetails.emailAddress() : null);

            // Create a new Address entity
            Address address = new Address();
            address.setCountry(contactDetails.country() != null ? contactDetails.country() : null);
            address.setArea(contactDetails.area() != null ? contactDetails.area() : null);
            address.setCity(contactDetails.city() != null ? contactDetails.city() : null);
            address.setStreet(contactDetails.street() != null ? contactDetails.street() : null);
            address.setStreetNumber(contactDetails.streetNumber() != null ? contactDetails.streetNumber() : null);

            // Save the Address entity
            addressRepository.save(address);
            // Set the billing address of the Contact entity
            contact.setBillingAddress(null);
            // Save the Contact entity
            contactRepository.saveUserContactDetails(contact);

            return ResponseEntity.ok("Details saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save details");
        }
    }
}
