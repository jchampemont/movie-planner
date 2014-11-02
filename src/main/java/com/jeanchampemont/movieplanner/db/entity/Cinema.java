package com.jeanchampemont.movieplanner.db.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
@NamedQuery(name = "Cinema.deleteAll",
    query = "DELETE FROM Cinema")
public class Cinema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 10, nullable = false)
    private String codeCinema;

    @Column(length = 75)
    private String name;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @OneToMany(mappedBy = "cinema")
    private List<Session> sessions;

    public Cinema(String codeCinema, String name, Double latitude, Double longitude) {
        this.codeCinema = codeCinema;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cinema)) return false;

        Cinema cinema = (Cinema) o;

        return codeCinema == cinema.codeCinema;
    }

    @Override
    public int hashCode() {
        return codeCinema.hashCode();
    }
}
