package com.iheb.sport.restController;


import com.iheb.sport.entities.Genre;
import com.iheb.sport.repos.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gen")
@CrossOrigin("*")
public class GenreRestController {
    @Autowired
    GenreRepository genreRepository;

    @RequestMapping(method= RequestMethod.GET)
    public List<Genre> getAllGenres()
    {
        return genreRepository.findAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Genre getGenreById(@PathVariable("id") Long id) {
        return genreRepository.findById(id).get();
    }
}
