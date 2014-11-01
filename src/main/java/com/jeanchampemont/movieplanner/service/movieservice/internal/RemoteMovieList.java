package com.jeanchampemont.movieplanner.service.movieservice.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoteMovieList {
    public List<RemoteMovie> films;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RemoteMovie {
        public String code_ugc;
        public String image_affiche;
        public String titre;
        public String duree;
        public List<Horaire> horaire;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Horaire {
        public String version;
        public String type_projection;
        public String description;
    }
}
