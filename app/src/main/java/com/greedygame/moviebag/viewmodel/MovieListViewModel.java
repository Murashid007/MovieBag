package com.greedygame.moviebag.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.greedygame.moviebag.model.Resource;
import com.greedygame.moviebag.model.MovieResult;
import com.greedygame.moviebag.repository.MovieListRepository;


public class MovieListViewModel extends ViewModel {
    private MutableLiveData<Resource<MovieResult>> mNowPlayingMovie;
    private MutableLiveData<Resource<MovieResult>> mPopularMovie;

    public MovieListViewModel() {
        MovieListRepository movieListRepository = new MovieListRepository();
        mNowPlayingMovie =  movieListRepository.getNowPlayingMovie();
        mPopularMovie =  movieListRepository.getPopularMovieResult();
    }

    public MutableLiveData<Resource<MovieResult>> getNowplayingMovie() {
        return mNowPlayingMovie;
    }

    public MutableLiveData<Resource<MovieResult>> getPopularMovieResult() {
        return mPopularMovie;
    }

}