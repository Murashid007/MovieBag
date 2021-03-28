package com.greedygame.moviebag.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.greedygame.moviebag.model.Movie;
import com.greedygame.moviebag.model.Resource;
import com.greedygame.moviebag.model.MovieResult;
import com.greedygame.moviebag.repository.MovieDetailsRepository;
import com.greedygame.moviebag.repository.MovieListRepository;


public class MovieDetailsViewModel extends ViewModel {
    private MutableLiveData<Resource<Movie>> mMovieDetails;
    private MutableLiveData<Resource<MovieResult>> mSimilarMovie;

    public MovieDetailsViewModel(String movie_id) {
        MovieDetailsRepository movieListRepository = new MovieDetailsRepository();
        mMovieDetails =  movieListRepository.getMovieDetails(movie_id);
        mSimilarMovie =  movieListRepository.getSimilarMovieResult(movie_id);
    }

    public MutableLiveData<Resource<Movie>> getMovieDetails() {
        return mMovieDetails;
    }

    public MutableLiveData<Resource<MovieResult>> getSimilarMovie() {
        return mSimilarMovie;
    }

}