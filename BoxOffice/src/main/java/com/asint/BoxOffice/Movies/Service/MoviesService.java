package com.asint.BoxOffice.Movies.Service;

import java.util.List;

import com.asint.BoxOffice.Movies.Model.Movie;

public interface MoviesService {

	public Movie registerMovie(Movie movie);
	
	public Movie getMovie(Integer movieId);
	
	public List<Movie> getAllMovies();
	
	public Movie removeMovie(Integer movieId);
	
}
