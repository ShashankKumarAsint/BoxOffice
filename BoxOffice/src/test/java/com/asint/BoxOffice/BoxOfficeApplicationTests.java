package com.asint.BoxOffice;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.asint.BoxOffice.Movies.Controller.MoviesController;
import com.asint.BoxOffice.Movies.Model.Genre;
import com.asint.BoxOffice.Movies.Model.Movie;

@SpringBootTest
class BoxOfficeApplicationTests {

	@Autowired
	MoviesController moviesController;
	
	Movie movie;
	
	@Test
	public void testRegisterMovie() {
		
		Movie movie = new Movie();		
		
		assertThrows(RuntimeException.class,()->moviesController.registerMovie(movie));
		
	}
	
	@Test
	public void testRegisterMovie2() {
		
		Movie movie = new Movie();	
		
		movie.setGenre(Genre.ADVENTURE);
		
		assertThrows(RuntimeException.class,()->moviesController.registerMovie(movie));
		
	}

	@Test
	public void testRegisterMovie3() {
		
		Movie movie = new Movie();	
		
		movie.setGenre(Genre.ADVENTURE);
		movie.setTitle("Marvels Avenger");
		
		assertThrows(RuntimeException.class,()->moviesController.registerMovie(movie));
		
	}
	
	@Test
	public void testRegisterMovie4() {
		
		Movie movie = new Movie();	
		
		movie.setGenre(Genre.ADVENTURE);
		movie.setTitle("Marvels Avenger");
		movie.setRuntimeMinutes(-1);
		
		assertThrows(RuntimeException.class,()->moviesController.registerMovie(movie));
		
	}
	
	@BeforeEach
	public void registerMovie() {
		
		Movie movie = new Movie();	
		
		movie.setGenre(Genre.ADVENTURE);
		movie.setTitle("Marvels Avenger");
		movie.setRuntimeMinutes(10);
		
		this.movie = moviesController.registerMovie(movie).getBody();
		
	}
	
//	@AfterEach
//	public void removeMovie() {
//		
//		moviesController.removeMovie(this.movie.getMovieId());
//		
//		
//	}
	@Test
	public void testViewMovie() {
		
		assertDoesNotThrow(()->moviesController.viewMovie(movie.getMovieId()));
		
	}
	
	@Test
	public void testViewMovie2() {
		
		assertThrows(RuntimeException.class, ()->moviesController.viewMovie(movie.getMovieId()+1));
		
	}
	
	@Test
	public void testViewAllMovie() {
		
		List<Movie> movies = moviesController.viewAllMovies().getBody();
		
		assertTrue(!movies.isEmpty());
	
	}
	
	@Test
	public void testViewMovie3() {
		
		assertThrows(RuntimeException.class, ()->moviesController.viewMovie(movie.getMovieId()+1));
		
	}
	
	@Test
	public void testRemoveMovie() {
		
		assertDoesNotThrow(()-> moviesController.removeMovie(this.movie.getMovieId()));
		
		assertThrows(RuntimeException.class, ()-> moviesController.removeMovie(this.movie.getMovieId()));
		
	}
	
	
}
