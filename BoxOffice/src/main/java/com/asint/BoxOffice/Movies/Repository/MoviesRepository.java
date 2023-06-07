package com.asint.BoxOffice.Movies.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asint.BoxOffice.Movies.Model.Movie;

public interface MoviesRepository extends JpaRepository<Movie, Integer>{

}
