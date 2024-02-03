package com.mycompany.entities.Movie;

public class MovieNotFoundException extends Throwable {
    public MovieNotFoundException(String message) {
        super(message);
    }
}
