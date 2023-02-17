package com.ajulibe.java.SpringBootApi.service;


import com.ajulibe.java.SpringBootApi.constants.MovieType;
import com.ajulibe.java.SpringBootApi.entity.Author;
import com.ajulibe.java.SpringBootApi.entity.Movie;
import com.ajulibe.java.SpringBootApi.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MoviesService {
    private final MoviesRepository moviesRepository;

    @Autowired
    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public void addMovies(Movie movieRequest) {
        Long id = movieRequest.getId();
        String title = movieRequest.getTitle();
        String recommended = movieRequest.getRecommended();
        Author author = movieRequest.getAuthor();
        String poster_path = movieRequest.getPoster_path();
        Date releaseDate = movieRequest.getReleaseDate();
        MovieType type = movieRequest.getType();

        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie(id, title, recommended, author, poster_path, releaseDate, type);
        movies.add(movie);
        moviesRepository.save(movies);
    }

    public void addBulkMovies(List<Movie> movieRequest) {
        List<Movie> movies = new ArrayList<>();

        for (Movie movie : movieRequest) {
            Long id = movie.getId();
            String title = movie.getTitle();
            String recommended = movie.getRecommended();
            Author author = movie.getAuthor();
            String poster_path = movie.getPoster_path();
            Date releaseDate = movie.getReleaseDate();
            MovieType type = movie.getType();

            Movie newMovie = new Movie(id, title, recommended, author, poster_path, releaseDate, type);
            movies.add(newMovie);
        }

        moviesRepository.save(movies);
    }

    public List<Movie> findAll() {
        return moviesRepository.findAll();
    }

    public List<Movie> findRecentMovie() {
        return moviesRepository.getRecentMovie();
    }

    public List<Movie> findRecommendedMovies() {
        return moviesRepository.getRecommendedMovies();
    }


    public void rateMovie (Long id, int rating) {
        moviesRepository.rateMovie(id, rating);
    }

}
