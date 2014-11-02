package com.jeanchampemont.movieplanner.db.entity;

import com.google.common.collect.ImmutableSet;
import lombok.*;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
public class Movie implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 10, nullable = false)
    private String codeUgc;

    @Column(length = 75)
    private String name;

    @Column(length = 200)
    private String posterUrl;

    @OneToMany(mappedBy = "movie")
    private List<Session> sessions;

    @Column
    private Duration duration;

    public Movie(String codeUgc, String name, String posterUrl, Duration duration) {
        this.codeUgc = codeUgc;
        this.name = name;
        this.posterUrl = posterUrl;
        this.duration = duration;
    }

    public void addSession(Session session) {
        if(this.sessions == null) {
            this.sessions = new ArrayList<>();
        }
        this.sessions.add(session);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        return codeUgc == movie.codeUgc;
    }

    @Override
    public int hashCode() {
        return codeUgc.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
