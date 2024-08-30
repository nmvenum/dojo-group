package com.nickmazurek.moviecourt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nickmazurek.moviecourt.models.Movie;
import com.nickmazurek.moviecourt.repositories.MovieRepo;


// Movie Service
@Service
public class MovieService {
	
	@Autowired
	private MovieRepo mRepo;
	
	//Create Movie
	
	public Movie createMovie(Movie m) {
		
		return mRepo.save(m);
		
		
	}
	
	//Get all Movies
	
	public List<Movie> getAllMovie(){
		return mRepo.findAll();
	}
	
	// Get one Movie
	
	public Movie getOneMovie(Long id) {
		Optional<Movie> thisMovie = mRepo.findById(id);
		
		if(thisMovie.isEmpty()) {
			return null;
		}
		
		return thisMovie.get();
	}
	
	//Delete Movie
	
	public void delete(Long id) {
		mRepo.deleteById(id);
	}
	
	

}
