package com.mycompany.entities.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired private MovieRepository repository;

    public List<Movie> listAll(){
        return (List<Movie>) repository.findAll();
    }

    public void save(Movie movie) {
        repository.save(movie);
    }

    public Movie get(Integer id) throws MovieNotFoundException {
        Optional<Movie> result=repository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new MovieNotFoundException("Could not find any movies with ID: " + id);
    }

    public void delete(Integer id_movie) throws MovieNotFoundException {
        Long count = repository.countByIdmovie(id_movie);
        if( count == null || count==0){
            throw new MovieNotFoundException("Could not find any movies with ID: " + id_movie);
        }
        try {
            repository.deleteById(id_movie);
        } catch (Exception e){
            if(e.getMessage().contains("foreign key")){
//                ra.addFlashAttribute("message","The movie can't be deleted cause of existing actors participating in it");
                throw new MovieNotFoundException("The movie can't be deleted cause of existing actors participating in it");
            }
        }
    }
}
