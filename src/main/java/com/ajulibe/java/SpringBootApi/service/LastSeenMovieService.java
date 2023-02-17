package com.ajulibe.java.SpringBootApi.service;

import com.ajulibe.java.SpringBootApi.entity.LastSeenMovie;
import com.ajulibe.java.SpringBootApi.repository.LastSeenMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LastSeenMovieService {

    private final LastSeenMovieRepository lastSeenMovieRepository;

    @Autowired
    public LastSeenMovieService(LastSeenMovieRepository lastSeenMovieRepository) {
        this.lastSeenMovieRepository = lastSeenMovieRepository;
    }

    public void saveLastSeenMovie(LastSeenMovie lastSeenMovie) {
        lastSeenMovieRepository.saveLastSeenMovie(lastSeenMovie);
    }

    public List<LastSeenMovie> getLastSeenMoviesByUser(String userId) {
        return lastSeenMovieRepository.findByUserId(userId);
    }

    public void deleteLastSeenMovie(LastSeenMovie lastSeenMovie) {
        lastSeenMovieRepository.deleteLastSeenMovie(lastSeenMovie);
    }
}


