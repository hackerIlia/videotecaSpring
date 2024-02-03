package com.mycompany.entities.Actors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;

    public Actor get(Integer id) throws ActorNotFoundException{
        Optional<Actor> result=actorRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ActorNotFoundException("Could not find any actors with ID: " + id);
    }

    public void delete(Integer id) throws ActorNotFoundException{
        actorRepository.deleteById(id);
    }
}
