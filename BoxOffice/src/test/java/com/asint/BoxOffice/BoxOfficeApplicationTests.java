package com.asint.BoxOffice;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.asint.BoxOffice.Movies.Controller.MoviesController;
import com.asint.BoxOffice.Movies.Model.Genre;
import com.asint.BoxOffice.Movies.Model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BoxOfficeApplication.class
)
@AutoConfigureMockMvc
class BoxOfficeApplicationTests {
	
	private MoviesController moviesController;
	
	
	private MockMvc mockMvc;
	
	ObjectMapper om = new ObjectMapper();
	
	private Movie movie;

//	@Before {NOTE : This before should run before each testcase but its not running and due to that mockMvc is null and it throws null pointer exception}
//	public void setup()throws Exception {
//		this.mockMvc = MockMvcBuilders.standaloneSetup(moviesController).build();
//	}
	
	@Autowired
	public BoxOfficeApplicationTests(MoviesController moviesController, MockMvc mockMvc) {
		super();
		this.moviesController = moviesController;
		this.mockMvc = mockMvc;
	}
	
	@Test
	public void testRegisterMovie() throws Exception {
		
		Movie movie = new Movie();
		movie.setGenre(Genre.ACTION);		
		
		String json = om.writeValueAsString(movie);
		 mockMvc.perform(post("/movies")
				.content(json)
				.contentType(APPLICATION_JSON))
		.andExpect(status().isBadRequest());
		
	}


	@Test
	public void testRegisterMovie2()throws Exception {
		
		Movie movie = new Movie();	
		
		movie.setGenre(Genre.ADVENTURE);
		movie.setTitle("Avengers End Game");
		
		String json = om.writeValueAsString(movie);
		 mockMvc.perform(post("/movies")
				.content(json)
				.contentType(APPLICATION_JSON))
		.andExpect(status().isBadRequest());
		 
		
	}

	@Test
	public void testRegisterMovie3()throws Exception {
		
		Movie movie = new Movie();	
		
		movie.setGenre(Genre.ADVENTURE);
		movie.setTitle("Marvels Avenger");
		movie.setRuntimeMinutes(-1);
		String json = om.writeValueAsString(movie);
		 mockMvc.perform(post("/movies")
				.content(json)
				.contentType(APPLICATION_JSON))
		.andExpect(status().isBadRequest());
		 
	}
	
	@Test
	@BeforeEach
	public void testRegisterMovie4()throws Exception {
		
		Movie movie = new Movie();	
		
		movie.setGenre(Genre.ADVENTURE);
		movie.setTitle("Marvels Avenger");
		movie.setRuntimeMinutes(10);
		
		String json = om.writeValueAsString(movie);
		 MvcResult result = mockMvc.perform(post("/movies")
				.content(json)
				.contentType(APPLICATION_JSON))
		.andExpect(status().isOk()).andReturn();
		 
		 String jsonResponse = result.getResponse().getContentAsString();
		 
		 this.movie = om.readValue(jsonResponse, Movie.class);
		 
	}


	@Test
	public void testViewMovie() throws Exception {
			
		mockMvc.perform(get("/movies/{movieId}", this.movie.getMovieId()))
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void testViewMovie2()throws Exception {
		
		mockMvc.perform(get("/movies/{movieId}", this.movie.getMovieId()+1))
		.andExpect(status().isBadRequest());

	}
	
	@Test
	public void testViewAllMovie()throws Exception {
		
		mockMvc.perform(get("/movies"))
		.andExpect(status().isOk());
		
	}
	
	
	@AfterEach
	public void testRemoveMovie()throws Exception {
		
		mockMvc.perform(delete("/movies/{movieId}", this.movie.getMovieId()))
		.andExpect(status().isOk());
		
	}
	@Test
	public void testRemoveMovie2()throws Exception {
		
		mockMvc.perform(delete("/movies/{movieId}", this.movie.getMovieId()+1))
		.andExpect(status().isBadRequest());
		
	}
	
	
}
