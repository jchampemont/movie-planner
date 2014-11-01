package com.jeanchampemont.movieplanner.db.repository;


import com.jeanchampemont.movieplanner.db.entity.Cinema;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends CrudRepository<Cinema, Long> {
    void deleteAll();
}
