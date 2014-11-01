package com.jeanchampemont.movieplanner.service.cinemaservice.internal;

import com.jeanchampemont.movieplanner.db.entity.Cinema;
import com.jeanchampemont.movieplanner.db.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {

    @Autowired
    private CinemaRemoteService cinemaRemoteService;

    @Autowired
    private CinemaRepository cinemaRepository;

    public List<Cinema> updateCinemasInDb() {
        List<Cinema> newCinemas = cinemaRemoteService.getCinemas();
        cinemaRepository.deleteAll();
        cinemaRepository.save(newCinemas);
        return newCinemas;
    }
}
