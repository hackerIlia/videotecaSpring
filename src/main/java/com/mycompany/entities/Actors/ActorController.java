package com.mycompany.entities.Actors;

import com.mycompany.entities.Movie.Movie;
import com.mycompany.entities.Movie.MovieService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ActorController {

    @Autowired
    private ActorRepository actorRepo;

    @Autowired
    private ActorService actorService;

    private MovieService movieRepo;

    public ActorController(MovieService movieRepo) {
        this.movieRepo = movieRepo;
    }


    @GetMapping("/actor/new")
    public String showNewActorForm(Model model){
        List<Movie> listMovies = movieRepo.listAll();
        model.addAttribute("actor",new Actor());
        model.addAttribute("listMovies",listMovies);

        return "actor_form";
    }

    @PostMapping("actor/save")
    public String saveActor(Actor actor,RedirectAttributes ra){
        actorRepo.save(actor);
        ra.addFlashAttribute("message","The actor has been saved successfully!");

        return "redirect:/actor";
    }

    @GetMapping("/actor")
    public String listActor(Model model){
        List<Actor> listActor = actorRepo.findAll();
        model.addAttribute("listActor",listActor);

        return "actor";
    }

    @GetMapping("actor/edit/{id}")
    public String showEditFormActor(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            List<Movie> listMovies = movieRepo.listAll();
            model.addAttribute("listMovies",listMovies);

            Actor actor=actorService.get(id);
            model.addAttribute("actor",actor);
            model.addAttribute("pageTitle","Edit actor (ID: "+ id + " )");
            return "actor_form";
        } catch (ActorNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());

            return "redirect:/actor";
        }
    }

    @GetMapping("/actor/delete/{id}")
    public String deleteActor(@PathVariable("id") Integer id,RedirectAttributes ra){
        try{
            actorService.delete(id);
            ra.addFlashAttribute("message","The actor with ID: " + id + " has been deleted");
        } catch (ActorNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/actor";
    }
}
