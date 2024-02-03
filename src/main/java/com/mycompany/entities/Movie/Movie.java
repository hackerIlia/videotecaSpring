package com.mycompany.entities.Movie;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmovie;
    @Column(nullable = false)
    private String name_movie;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date release_date;
    @Column(nullable = false)
    private String release_country;

    public int getIdmovie() {
        return idmovie;
    }

    public void setIdmovie(int id_movie) {
        this.idmovie = id_movie;
    }

    public String getName_movie() {
        return name_movie;
    }

    public void setName_movie(String name_movie) {
        this.name_movie = name_movie;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getRelease_country() {
        return release_country;
    }

    public void setRelease_country(String release_country) {
        this.release_country = release_country;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id_movie=" + idmovie +
                ", name_movie='" + name_movie + '\'' +
                ", release_date=" + release_date +
                ", release_country='" + release_country + '\'' +
                '}';
    }
}
