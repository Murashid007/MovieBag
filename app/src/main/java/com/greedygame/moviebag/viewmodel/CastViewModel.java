package com.greedygame.moviebag.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.greedygame.moviebag.model.CastResult;
import com.greedygame.moviebag.model.Movie;
import com.greedygame.moviebag.model.Resource;
import com.greedygame.moviebag.model.MovieResult;
import com.greedygame.moviebag.model.ReviewResult;
import com.greedygame.moviebag.repository.CastRepository;
import com.greedygame.moviebag.repository.MovieDetailsRepository;
import com.greedygame.moviebag.repository.MovieListRepository;
import com.greedygame.moviebag.repository.ReviewRepository;


public class CastViewModel extends ViewModel {
    private MutableLiveData<Resource<CastResult>> castResult;

    public CastViewModel(String movie_id) {
        CastRepository castRepository = new CastRepository();
        castResult =  castRepository.getCast(movie_id);
    }

    public MutableLiveData<Resource<CastResult>> getCast() {
        return castResult;
    }

}