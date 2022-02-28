package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieBasicDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Transactional(readOnly=true)
	public MovieDetailsDTO findById(Long id){
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new MovieDetailsDTO(entity);
	}

	@Transactional(readOnly=true)
	public Page<MovieBasicDTO> findPagedByGenre(Long genreId, Pageable pageable){
		genreId = (genreId==0)? null : genreId;
		Page<Movie> page = repository.find(genreId, pageable);
		return page.map(obj -> new MovieBasicDTO(obj));
	}
	
	@Transactional(readOnly=true)
	public List<ReviewDTO> findByMovieId(Long id){
		List<Review> list = reviewRepository.findByMovieId(id);
		return list.stream().map(obj -> new ReviewDTO(obj)).collect(Collectors.toList());
	}

}