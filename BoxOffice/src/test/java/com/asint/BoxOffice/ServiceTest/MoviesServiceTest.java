package com.asint.BoxOffice.ServiceTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.asint.BoxOffice.Movies.Model.Genre;
import com.asint.BoxOffice.Movies.Model.Movie;
import com.asint.BoxOffice.Movies.Repository.MoviesRepository;
import com.asint.BoxOffice.Movies.Service.MoviesService;

@SpringBootTest
public class MoviesServiceTest {

	@MockBean
	private MoviesRepository movieRepository;
	
	private MoviesService moviesService;

	@Autowired
	public MoviesServiceTest(MoviesService moviesService) {
		super();
		this.moviesService = moviesService;
	}
	
	@Test
	public void testRegisterMovie() {
		
		Movie movie = new Movie();
		movie.setGenre(Genre.ACTION);
		movie.setRuntimeMinutes(10);
		movie.setTitle("Superman");
		when(movieRepository.save(movie)).thenReturn(movie);
		
		assertEquals(movie, moviesService.registerMovie(movie));
		
	}
	
	@Test
	public void testRegisterMovie2() {
		
		Movie movie = new Movie();
		movie.setGenre(Genre.ACTION);
		movie.setRuntimeMinutes(-1);
		movie.setTitle("Superman");
		
		assertThrows(RuntimeException.class, ()->moviesService.registerMovie(movie));
		
	}
	@Test
	public void testGetMovie() {
		
		Optional<Movie> movieOpt = Optional.of(new Movie());
		movieOpt.get().setGenre(Genre.ACTION);
		movieOpt.get().setRuntimeMinutes(10);
		movieOpt.get().setTitle("Superman");
		
		when(movieRepository.findById(1)).thenReturn(movieOpt);
		
		assertTrue(moviesService.getMovie(1).equals(movieOpt.get()));
		
	}
	@Test
	public void testGetMovie2() {
		
		when(movieRepository.findById(1)).thenReturn(null);
		
		assertThrows(RuntimeException.class, ()-> moviesService.getMovie(1));
		
	}
	
	@Test
	public void testGetAllMovies() {
		
		when(movieRepository.findAll()).thenReturn(null);
		
		assertThrows(RuntimeException.class, ()-> moviesService.getAllMovies());
		
	}
	
	@Test
	public void testGetAllMovies2() {
		
		Movie movie = new Movie();
		
		List<Movie> movies = new ArrayList<>();
		
		movies.add(movie);
		
		when(movieRepository.findAll()).thenReturn(movies);
		
		List<Movie> moviesResponse = moviesService.getAllMovies();
		
		assertTrue(!moviesResponse.isEmpty());
		
	}
	
	@Test
	public void testRemoveMovie() {
		
		Optional<Movie> movieOpt = Optional.of(new Movie());
		movieOpt.get().setGenre(Genre.ACTION);
		movieOpt.get().setRuntimeMinutes(10);
		movieOpt.get().setTitle("Superman");
		
		Movie movie = movieOpt.get();

		when(movieRepository.findById(1)).thenReturn(movieOpt);
		
		assertTrue(movie.equals(moviesService.removeMovie(1)));
		
	}
	
	@Test
	public void testRemoveMovie2() {

		when(movieRepository.findById(1)).thenReturn(null);
		
		assertThrows(RuntimeException.class, ()-> moviesService.removeMovie(1));
		
	}
	
}
