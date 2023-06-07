package com.asint.BoxOffice.Movies.Model;


	import jakarta.persistence.Entity;
	import jakarta.persistence.EnumType;
	import jakarta.persistence.Enumerated;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;

	@Entity
	public class Movie {
		
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer movieId;

	    private String title;

	    private Integer runtimeMinutes;
	    
	    @Enumerated(EnumType.STRING)
	    private Genre genre;
	    
	    

		public Integer getMovieId() {
			return movieId;
		}

		public void setMovieId(Integer movieId) {
			this.movieId = movieId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Integer getRuntimeMinutes() {
			return runtimeMinutes;
		}

		public void setRuntimeMinutes(Integer runtimeMinutes) {
			this.runtimeMinutes = runtimeMinutes;
		}

		public Genre getGenre() {
			return genre;
		}

		public void setGenre(Genre genre) {
			this.genre = genre;
		}
	    
	    


	}

	


