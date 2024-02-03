package com.mycompany.entities.Actors;

import com.mycompany.entities.Movie.Movie;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_actor;

    @Column(nullable = false)
    private String name_actor;

    @Column(nullable = false)
    private Date date_of_birth_actor;

    @ManyToOne
    @JoinColumn(name = "id_movie")
    private Movie movie;


    public int getId_actor() {
        return id_actor;
    }

    public void setId_actor(int id_actor) {
        this.id_actor = id_actor;
    }

    public String getName_actor() {
        return name_actor;
    }

    public void setName_actor(String name_actor) {
        this.name_actor = name_actor;
    }

    public Date getDate_of_birth_actor() {
        return date_of_birth_actor;
    }

    public void setDate_of_birth_actor(Date date_of_birth_actor) {
        this.date_of_birth_actor = date_of_birth_actor;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
