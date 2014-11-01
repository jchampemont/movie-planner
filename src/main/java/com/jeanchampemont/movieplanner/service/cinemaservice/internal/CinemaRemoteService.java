package com.jeanchampemont.movieplanner.service.cinemaservice.internal;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.jeanchampemont.movieplanner.db.entity.Cinema;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CinemaRemoteService {
    @Value("${ugc.api.prefix}")
    private String apiPrefix;

    @Value("${ugc.api.globaluser}")
    private String globalUser;

    @Autowired
    private DurationUtils durationUtils;

    private Log log = LogFactory.getLog(CinemaRemoteService.class);

    @Cacheable("cinemas")
    public List<Cinema> getCinemas() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("globalUser", globalUser);
        RemoteCinemaList remoteMovieList = restTemplate.postForObject(apiPrefix + "getAllComplexeByFilter", params, RemoteCinemaList.class);
        return ImmutableList.copyOf(map(remoteMovieList));
    }

    private Set<Cinema> map(RemoteCinemaList remoteMovieList) {
        Set<Cinema> result = new HashSet<>();
        for(RemoteCinemaList.RemoteCinema cinema : remoteMovieList.complexes) {
            result.add(new Cinema(cinema.code_complexe, cinema.libelle, cinema.latitude, cinema.longitude));
        }
        return result;
    }
}
