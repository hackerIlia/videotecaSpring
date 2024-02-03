package com.mycompany.entities.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MovieController {
    @Autowired private MovieService service;

    @GetMapping("/data")
    public String showMovieList(Model model){
        List<Movie> movieList= service.listAll();
        model.addAttribute("listMovies",movieList);

        return "movies";
    }

    @GetMapping("/movies/new")
    public String showNewForm(Model model){
        model.addAttribute("movie",new Movie());
        model.addAttribute("pageTitle","Add new movie");
        return "movie_form";
    }

    @PostMapping("/movies/save")
    public String saveMovie(Movie movie, RedirectAttributes ra){
        service.save(movie);
        ra.addFlashAttribute("message","The movie has been saved successfully!");

         return "redirect:/data";
    }

    @GetMapping("/movies/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model,RedirectAttributes ra){
        try {
            Movie movie=service.get(id);
            model.addAttribute("movie",movie);
            model.addAttribute("pageTitle","Edit movie (ID: "+id+" )");
            return "movie_form";
        } catch (MovieNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());

            return "redirect:/data";
        }
    }

    @GetMapping("/movies/delete/{id}")
    public String deleteMovie(@PathVariable("id") Integer id,RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message","The movie with ID: "+ id+" has been deleted");
        } catch (MovieNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/data";
    }
}