package com.ajulibe.java.SpringBootApi.dto;


public record ContactDetailsDTO(

        String userId,
        String firstName,
        String lastName,
        String phoneNumber,
        String emailAddress,
        String country,
        String area,
        String city,
        String street,
        String streetNumber

) {
}