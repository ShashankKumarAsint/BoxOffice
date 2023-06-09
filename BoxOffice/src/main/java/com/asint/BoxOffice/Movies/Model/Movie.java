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


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Movie other = (Movie) obj;
			if (genre != other.genre)
				return false;
			if (movieId == null) {
				if (other.movieId != null)
					return false;
			} else if (!movieId.equals(other.movieId))
				return false;
			if (runtimeMinutes == null) {
				if (other.runtimeMinutes != null)
					return false;
			} else if (!runtimeMinutes.equals(other.runtimeMinutes))
				return false;
			if (title == null) {
				if (other.title != null)
					return false;
			} else if (!title.equals(other.title))
				return false;
			return true;
		}
	    
	    


	}

	


