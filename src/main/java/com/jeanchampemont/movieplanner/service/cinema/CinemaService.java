package com.jeanchampemont.movieplanner.service.cinema;

import com.google.common.collect.ImmutableList;
import com.jeanchampemont.movieplanner.db.entity.Cinema;
import com.jeanchampemont.movieplanner.db.repository.CinemaRepository;
import com.jeanchampemont.movieplanner.service.cinema.internal.CinemaRemoteService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {

    private Log log = LogFactory.getLog(CinemaService.class);

    @Autowired
    private CinemaRemoteService cinemaRemoteService;

    @Autowired
    private CinemaRepository cinemaRepository;

    public List<Cinema> getCinemas() {
        List<Cinema> result = ImmutableList.copyOf(cinemaRepository.findAll());
        if(result.size() == 0) {
            log.info("No cinemas in DB... updating !");
            result = updateCinemasInDb();
        }
        return result;
    }

    public List<Cinema> updateCinemasInDb() {
        List<Cinema> newCinemas = cinemaRemoteService.getCinemas();
        cinemaRepository.deleteAll();
        cinemaRepository.save(newCinemas);
        return newCinemas;
    }
}
