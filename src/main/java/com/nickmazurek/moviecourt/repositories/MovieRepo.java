package com.nickmazurek.moviecourt.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nickmazurek.moviecourt.models.Movie;



public interface MovieRepo extends CrudRepository<Movie, Long> {
	
	List<Movie> findAll();

}
