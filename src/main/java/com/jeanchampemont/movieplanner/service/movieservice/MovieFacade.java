package com.jeanchampemont.movieplanner.service.movieservice;

import com.jeanchampemont.movieplanner.db.entity.Cinema;
import com.jeanchampemont.movieplanner.db.entity.Movie;
import com.jeanchampemont.movieplanner.service.cinema.CinemaService;
import com.jeanchampemont.movieplanner.service.movieservice.internal.MovieRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MovieFacade {

    @Autowired
    private MovieRemoteService movieRemoteService;

    @Autowired
    private CinemaService cinemaService;

    public Set<Movie> getAllMoviesAtCinema(Cinema cinema) {
        return movieRemoteService.getAllMoviesAtCinema(cinema);
    }
}
