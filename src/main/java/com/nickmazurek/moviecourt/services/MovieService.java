package com.nickmazurek.moviecourt.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nickmazurek.moviecourt.models.Movie;
import com.nickmazurek.moviecourt.models.User;
import com.nickmazurek.moviecourt.repositories.MovieRepo;


// Movie Service
@Service
public class MovieService {
    
    @Autowired
    private UserService uServ;
    
    @Autowired
    private MovieRepo mRepo;
    
    //Create Movie
    public Movie createMovie(Movie movie, Long userId) {
        User user = uServ.getUserById(userId);
        movie.setUser(user);
        return mRepo.save(movie);
            }
    
    //Get all Movies
    public List<Movie> getAllMovie(){
        return mRepo.findAll();
    }
    
    // Get one Movie
    public Movie getOneMovie(Long movieId) {
        Optional<Movie> thisMovie = mRepo.findById(movieId);
        
        if(thisMovie.isEmpty()) {
            return null;
        }
        return thisMovie.get();
    }
    
    // Update Movie
    public Movie updateMovie(Movie movie, Long movieId, Long userId) {
        User user = uServ.getUserById(userId);
        movie.setId(movieId);
        movie.setUser(user);
        return mRepo.save(movie);
    }
    
    //Delete Movie
    public void delete(Long movieId) {
        mRepo.deleteById(movieId);
    }
    
    

}