package com.jeanchampemont.movieplanner.service.movieservice;

import com.jeanchampemont.movieplanner.Application;
import com.jeanchampemont.movieplanner.db.entity.Cinema;
import com.jeanchampemont.movieplanner.db.entity.Movie;
import com.jeanchampemont.movieplanner.service.cinema.CinemaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MovieFacadeIntegrationTests {
    @Autowired
    private MovieFacade sut;

    @Autowired
    private CinemaService cinemaService;

    private Log log = LogFactory.getLog(MovieFacadeIntegrationTests.class);

    @Test
    public void testAllMoviesAtCinema() {
        Random rand = new Random();
        List<Cinema> cinemas = cinemaService.getCinemas();
        int randomCine = rand.nextInt(cinemas.size());
        log.info("Cinema : " + cinemas.get(randomCine).getName());
        Set<Movie> movies = sut.getAllMoviesAtCinema(cinemas.get(randomCine));
        log.info(movies);
        assertFalse(movies.isEmpty());
    }
}
