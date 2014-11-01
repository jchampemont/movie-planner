package com.jeanchampemont.movieplanner.service.cinemaservice.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoteCinemaList {
    public List<RemoteCinema> complexes;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RemoteCinema {
        public String code_complexe;
        public String libelle;
        public Double latitude;
        public Double longitude;
    }
}
