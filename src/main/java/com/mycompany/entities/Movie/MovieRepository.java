package com.mycompany.entities.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Long countByIdmovie(Integer id_movie);
}
