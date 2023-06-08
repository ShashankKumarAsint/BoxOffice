package com.asint.BoxOffice.Movies.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asint.BoxOffice.Movies.Model.Movie;
import com.asint.BoxOffice.Movies.Service.MoviesService;

@RestController
@RequestMapping("/movies")
public class MoviesController {
	
	
	private final MoviesService moviesService;
	
	
	public MoviesController(MoviesService moviesService) {
		super();
		this.moviesService = moviesService;
	}

	@PostMapping
	public ResponseEntity<Movie> registerMovie(@RequestBody Movie movie){
		
		if(movie.getTitle()==null || movie.getGenre()==null || movie.getRuntimeMinutes()==null ) throw new RuntimeException("Please enter all the required details");
		
		Movie registeredMovie = moviesService.registerMovie(movie);
		
		return new ResponseEntity<>(registeredMovie, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{movieId}")
	public ResponseEntity<Movie> viewMovie(@PathVariable Integer movieId){
				
		Movie movie = moviesService.getMovie(movieId);
		
		return new ResponseEntity<>(movie, HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Movie>> viewAllMovies(){
				
		List<Movie> movies = moviesService.getAllMovies();
		
		return new ResponseEntity<>(movies, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{movieId}")
	public ResponseEntity<Movie> removeMovie(@PathVariable Integer movieId){
				
		Movie removedMovie = moviesService.removeMovie(movieId);
		
		return new ResponseEntity<>(removedMovie, HttpStatus.CREATED);
		
	}

}
