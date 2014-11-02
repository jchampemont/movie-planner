package com.jeanchampemont.movieplanner.service.cinema;

import com.jeanchampemont.movieplanner.Application;
import com.jeanchampemont.movieplanner.db.entity.Cinema;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CinemaServiceIntegrationTests {
    @Autowired
    private CinemaService sut;

    private Log log = LogFactory.getLog(CinemaServiceIntegrationTests.class);

    @Test
    public void testGetCinemas() {
        List<Cinema> cinemas = sut.getCinemas();
        assertFalse(cinemas.isEmpty());
    }

    @Test
    public void testUpdateDb() {
        List<Cinema> cinemas = sut.updateCinemasInDb();
        assertFalse(cinemas.isEmpty());
    }
}
