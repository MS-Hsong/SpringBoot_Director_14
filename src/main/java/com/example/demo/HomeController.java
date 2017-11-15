package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("/")
    public String loadData(Model model){
        Director d1 = new Director();
        d1.setName("Stephen Spilburg");

        Movie movie_obj1 = new Movie("ABC", 1977, "a series of movies");
        Movie movie_obj2 = new Movie("DEF", 2000, "a series of movies");

        Set<Movie> movies = new HashSet<>();
        movies.add(movie_obj1);
        movies.add(movie_obj2);
        d1.setMovies(movies);

        directorRepository.save(d1);
        model.addAttribute("directors", directorRepository.findAll());

        // work on the movie object
        movie_obj1.setDirector(d1);
        movie_obj2.setDirector(d1);
        movieRepository.save(movie_obj1);
        movieRepository.save(movie_obj2);
        model.addAttribute("movies", movieRepository.findAll());

        return "index";

    }
}
