package com.example.moviesDB.app.Service;

import com.example.moviesDB.app.Model.Movie;
import com.example.moviesDB.app.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getSingleMovie(String ImdbId) {
        return movieRepository.findMovieByImdbId(ImdbId);
    }

    public void deleteSingleMovie(String ObjectId) {
        movieRepository.deleteMovieById(ObjectId);
    }
}
