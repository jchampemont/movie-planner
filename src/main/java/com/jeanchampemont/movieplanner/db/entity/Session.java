package com.jeanchampemont.movieplanner.db.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Cinema cinema;

    @Column(length = 20)
    private String version;

    @Column(length = 20)
    private String typeProjection;

    @Column
    private LocalDateTime start;

    @Column
    private LocalDateTime end;

    public Session(Cinema cinema, Movie movie, String version, String typeProjection, LocalDateTime start, LocalDateTime end) {
        this.cinema = cinema;
        this.movie = movie;
        this.version = version;
        this.typeProjection = typeProjection;
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;

        Session session = (Session) o;

        if (cinema != null ? !cinema.equals(session.cinema) : session.cinema != null) return false;
        if (end != null ? !end.equals(session.end) : session.end != null) return false;
        if (movie != null ? !movie.equals(session.movie) : session.movie != null) return false;
        if (start != null ? !start.equals(session.start) : session.start != null) return false;
        if (typeProjection != null ? !typeProjection.equals(session.typeProjection) : session.typeProjection != null)
            return false;
        if (version != null ? !version.equals(session.version) : session.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (movie != null ? movie.hashCode() : 0);
        result = 31 * result + (cinema != null ? cinema.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (typeProjection != null ? typeProjection.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}
