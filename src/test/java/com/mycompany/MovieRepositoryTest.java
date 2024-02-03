package com.mycompany;

import com.mycompany.entities.Movie.Movie;
import com.mycompany.entities.Movie.MovieRepository;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository repository;

    @Test
    public void testAddNew(){
        Movie movie=new Movie();
//        movie.setId_movie(1);
        movie.setName_movie("Anatomy of a Fall");
        movie.setRelease_date(new Date(2023-1900,6-1,2));
        movie.setRelease_country("USA");

        Movie savedMovie1=repository.save(movie);

        org.assertj.core.api.Assertions.assertThat(savedMovie1).isNotNull();
        Assertions.assertThat(savedMovie1.getIdmovie()).isGreaterThan(0);
    }

    @Test
    public void listAll(){
        Iterable<Movie> movies=repository.findAll();
        Assertions.assertThat(movies).hasSizeGreaterThan(0);

        for (Movie movie:movies
             ) {
            System.out.println(movie);
            
        }
    }

    @Test
    public void testUpdate(){
        Integer movieId=1;
        Optional<Movie> optionalMovie=repository.findById(movieId);
        Movie movie= optionalMovie.get();
        repository.save(movie);

        Movie updatedMovie=repository.findById(movieId).get();
    }

    @Test
    public void testGet(){
        Integer movieId=2;
        Optional<Movie> optionalMovie=repository.findById(movieId);
        Assertions.assertThat(optionalMovie).isPresent();
        System.out.println(optionalMovie.get());
    }

    @Test
    public void testDelete(){
        Integer movieId=2;
        repository.deleteById(movieId);

        Optional<Movie> optionalMovie=repository.findById(movieId);
        Assertions.assertThat(optionalMovie).isNotPresent();
    }

}
