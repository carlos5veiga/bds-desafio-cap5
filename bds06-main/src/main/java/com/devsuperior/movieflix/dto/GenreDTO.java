package com.devsuperior.movieflix.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;

public class GenreDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private List<Movie> movies = new ArrayList<>();
	
	public GenreDTO() {
	}

	public GenreDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public GenreDTO(Genre entity) {
		id = entity.getId();
		name = entity.getName();
	}
	
	/*public GenreDTO(Genre entity, Set<Movie> movies) {
		this(entity);
		movies.forEach(mov -> this.movies.add(new MovieDTO(mov)));
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public String getName() {
		return name;
	}
}
