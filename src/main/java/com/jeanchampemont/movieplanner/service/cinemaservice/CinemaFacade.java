package com.jeanchampemont.movieplanner.service.cinemaservice;

import com.jeanchampemont.movieplanner.db.entity.Cinema;
import com.jeanchampemont.movieplanner.service.cinemaservice.internal.CinemaRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CinemaFacade {

    @Autowired
    private CinemaRemoteService remoteService;

    public List<Cinema> getCinemas() {
        return remoteService.getCinemas();
    }
}
