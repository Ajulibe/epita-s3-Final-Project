package com.ajulibe.java.SpringBootApi.controller;

import com.ajulibe.java.SpringBootApi.dto.MovieRating;
import com.ajulibe.java.SpringBootApi.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RatingController {
    private final MoviesService moviesService;

    @Autowired
    public RatingController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }


    @PostMapping("/rate-movie")
    public ResponseEntity<?> rateMovie(@RequestBody MovieRating movieRating) {
        try {
            moviesService.rateMovie(movieRating.id(), movieRating.rating());
            return ResponseEntity.ok("Movie rating updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update movie rating");
        }
    }
}


