package com.ajulibe.java.SpringBootApi.controller;


import com.ajulibe.java.SpringBootApi.entity.Movie;
import com.ajulibe.java.SpringBootApi.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class MoviesController {


    private final MoviesService moviesService;

    @Autowired
    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @PostMapping("/add-one-movie")
    public ResponseEntity<?> addMovies(@RequestBody Movie movieRequest) {
        moviesService.addMovies(movieRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-bulk-movies")
    public ResponseEntity<?> addBulkMovies(@RequestBody List<Movie> movieRequest) {
        moviesService.addBulkMovies(movieRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<Movie>> findAll() {
        List<Movie> movies = moviesService.findAll();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/recent-movies")
    public ResponseEntity<List<Movie>> findRecentMovies() {
        List<Movie> movies = moviesService.findRecentMovie();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/recommended-movies")
    public ResponseEntity<List<Movie>> findRecommendedMovies() {
        List<Movie> movies = moviesService.findRecommendedMovies();
        return ResponseEntity.ok(movies);
    }


}
