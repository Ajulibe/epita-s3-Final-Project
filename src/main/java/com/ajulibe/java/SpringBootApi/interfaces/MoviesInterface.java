package com.ajulibe.java.SpringBootApi.interfaces;

import com.ajulibe.java.SpringBootApi.entity.Movie;

import java.util.List;

public interface MoviesInterface {
    public List<Movie> findAll();

    public void save(List<Movie> movies);

}