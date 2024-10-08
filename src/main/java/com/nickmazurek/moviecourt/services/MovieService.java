package com.codingdojo.moviecourt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.moviecourt.models.Comment;
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
    
    @Autowired
    private CommentService cServ;
    
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
        
        existingMovie.getComments().clear();
        for (Comment comment : movie.getComments()) {
            existingMovie.addComment(comment);
        }

        return mRepo.save(existingMovie);
    }
    
    //Delete Movie
    public void delete(Long movieId) {
        mRepo.deleteById(movieId);
    }
    
    public void addCommentToMovie(Long movieId, Comment comment) {
        Movie movie = getOneMovie(movieId);
        movie.addComment(comment);
        mRepo.save(movie);
    }

    public void removeCommentFromMovie(Long movieId, Long commentId) {
        Movie movie = getOneMovie(movieId);
        Comment comment = cServ.getCommentById(commentId);
        movie.removeComment(comment);
        mRepo.save(movie);
        cServ.deleteComment(commentId);
    }

    

}
