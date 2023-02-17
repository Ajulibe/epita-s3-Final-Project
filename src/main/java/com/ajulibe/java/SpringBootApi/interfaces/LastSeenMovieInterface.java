package com.ajulibe.java.SpringBootApi.interfaces;

import com.ajulibe.java.SpringBootApi.entity.LastSeenMovie;
import com.ajulibe.java.SpringBootApi.entity.Movie;

import java.util.List;

public interface LastSeenMovieInterface {
    List<LastSeenMovie> findByUserId(String userId);
}
