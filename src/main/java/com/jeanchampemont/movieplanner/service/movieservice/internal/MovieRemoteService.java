package com.jeanchampemont.movieplanner.service.movieservice.internal;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.jeanchampemont.movieplanner.db.entity.Cinema;
import com.jeanchampemont.movieplanner.db.entity.Movie;
import com.jeanchampemont.movieplanner.db.entity.Session;
import com.jeanchampemont.movieplanner.utils.DurationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieRemoteService {
    @Value("${ugc.api.prefix}")
    private String apiPrefix;

    @Value("${ugc.api.version}")
    private String version;

    @Value("${cinema.adstimeminutes}")
    private int adsTimeMinutes;

    @Autowired private DurationUtils durationUtils;

    private Log log = LogFactory.getLog(MovieRemoteService.class);

    @Cacheable("movies")
    public Set<Movie> getAllMoviesAtCinema(Cinema cinema) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code_complexe", cinema.getCodeCinema());
        RemoteMovieList remoteMovieList = restTemplate.postForObject(apiPrefix + "getAllFilmsByFilter", params, RemoteMovieList.class);
        return ImmutableSet.copyOf(map(remoteMovieList, cinema));
    }

    private Set<Movie> map(RemoteMovieList remoteMovieList, Cinema cinema) {
        Set<Movie> result = new HashSet<>();
        for(RemoteMovieList.RemoteMovie remoteMovie : remoteMovieList.films) {
            if(remoteMovie.duree != null) {
                Movie m = new Movie(remoteMovie.code_ugc, remoteMovie.titre, remoteMovie.image_affiche, durationUtils.parse(remoteMovie.duree));
                m = map(cinema, m, remoteMovie.horaire);
                result.add(m);
            } else {
                log.info("Missing duration for Movie " + remoteMovie.titre +", movie is ignored.");
            }
        }
        return result;
    }

    private Movie map(Cinema cinema, Movie movie, List<RemoteMovieList.Horaire> horaires) {
        for(RemoteMovieList.Horaire horaire : horaires) {
            String description = horaire.description;
            Multimap<String, String> sessionsByDayOfWeek = ArrayListMultimap.create();
            String[] sessionByDayOfWeekStrArray = description.split("\n");
            for(String sessionDay : sessionByDayOfWeekStrArray) {
                String[] days = sessionDay.substring(0, sessionDay.indexOf(":")).trim().split("-");
                String[] sessions = sessionDay.substring(sessionDay.indexOf(":")+1).trim().split(", ");
                for(String day : days) {
                    sessionsByDayOfWeek.putAll(day, Lists.newArrayList(sessions));
                }
            }

            LocalDate dateIncrement = LocalDate.now();
            do {
                Set<String> sessions = new HashSet<>();
                sessions.addAll(sessionsByDayOfWeek.get("TLJ"));
                switch(dateIncrement.getDayOfWeek()) {
                    case MONDAY:
                        sessions.addAll(sessionsByDayOfWeek.get("Lun"));
                        break;
                    case TUESDAY:
                        sessions.addAll(sessionsByDayOfWeek.get("Mar"));
                        break;
                    case WEDNESDAY:
                        sessions.addAll(sessionsByDayOfWeek.get("Mer"));
                        break;
                    case THURSDAY:
                        sessions.addAll(sessionsByDayOfWeek.get("Jeu"));
                        break;
                    case FRIDAY:
                        sessions.addAll(sessionsByDayOfWeek.get("Ven"));
                        break;
                    case SATURDAY:
                        sessions.addAll(sessionsByDayOfWeek.get("Sam"));
                        break;
                    case SUNDAY:
                        sessions.addAll(sessionsByDayOfWeek.get("Dim"));
                        break;
                }
                for(String sessionStr : sessions) {
                    String[] hourMins = sessionStr.split(":");
                    LocalTime startTime = LocalTime.of(Integer.parseInt(hourMins[0]), Integer.parseInt(hourMins[1]));
                    LocalTime endTime = startTime.plus(movie.getDuration()).plusMinutes(adsTimeMinutes);
                    movie.addSession(new Session(cinema, movie, horaire.version, horaire.type_projection, LocalDateTime.of(dateIncrement, startTime), LocalDateTime.of(dateIncrement, endTime)));
                }
                dateIncrement = dateIncrement.plusDays(1);
            } while(!dateIncrement.getDayOfWeek().equals(DayOfWeek.WEDNESDAY));
        }
        return movie;
    }
}
