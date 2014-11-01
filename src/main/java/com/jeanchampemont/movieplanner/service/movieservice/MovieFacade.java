package com.jeanchampemont.movieplanner.service.movieservice;

import com.jeanchampemont.movieplanner.db.entity.Cinema;
import com.jeanchampemont.movieplanner.db.entity.Movie;
import com.jeanchampemont.movieplanner.service.cinemaservice.CinemaFacade;
import com.jeanchampemont.movieplanner.service.movieservice.internal.MovieRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MovieFacade {

    @Autowired
    private MovieRemoteService movieRemoteService;

    @Autowired
    private CinemaFacade cinemaFacade;

    public Set<Movie> getAllMoviesAtCinema(Cinema cinema) {
        return movieRemoteService.getAllMoviesAtCinema(cinema);
    }
}
