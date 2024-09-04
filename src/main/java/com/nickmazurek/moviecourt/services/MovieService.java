package com.codingdojo.moviecourt.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codingdojo.moviecourt.models.Movie;
import com.codingdojo.moviecourt.models.User;
import com.codingdojo.moviecourt.repositories.MovieRepo;


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
        Movie existingMovie = mRepo.findById(movieId)
            .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        
        existingMovie.setMovieTitle(movie.getMovieTitle());
        existingMovie.setMovieRating(movie.getMovieRating());
        existingMovie.setMovieComment(movie.getMovieComment());
        existingMovie.setComments(movie.getComments()); // Update the comments list

        return mRepo.save(existingMovie);
    }
    
    //Delete Movie
    public void delete(Long movieId) {
        mRepo.deleteById(movieId);
    }
    
    

}
