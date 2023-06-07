package com.asint.BoxOffice.Movies.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asint.BoxOffice.Movies.Model.Movie;
import com.asint.BoxOffice.Movies.Repository.MoviesRepository;

@Service
public class MoviesServiceImpl implements MoviesService{
	
	
	@Autowired
	MoviesRepository moviesRepository;

	@Override
	public Movie registerMovie(Movie movie) {
		
		if(movie.getRuntimeMinutes()<=0) throw new RuntimeException("Runtime minutes cannot be less than or equal to 0");
		
		return moviesRepository.save(movie);
		
	}

	@Override
	public Movie getMovie(Integer movieId) {
		
		return moviesRepository.findById(movieId).orElseThrow(()-> new RuntimeException("Invalid movie id : "+movieId));

	}

	@Override
	public List<Movie> getAllMovies() {
		
		List<Movie> movies = moviesRepository.findAll();
		
		if(movies.isEmpty()) throw new RuntimeException("No movies found");
		
		return movies;
		
	}

	@Override
	public Movie removeMovie(Integer movieId) {
		
		Movie movie = moviesRepository.findById(movieId).orElseThrow(()-> new RuntimeException("Invalid movie id : "+movieId));
		
		moviesRepository.delete(movie);
		
		return movie;
		
	}

}

