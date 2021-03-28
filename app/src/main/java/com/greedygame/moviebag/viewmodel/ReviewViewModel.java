package com.greedygame.moviebag.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.greedygame.moviebag.model.Movie;
import com.greedygame.moviebag.model.Resource;
import com.greedygame.moviebag.model.MovieResult;
import com.greedygame.moviebag.model.ReviewResult;
import com.greedygame.moviebag.repository.MovieDetailsRepository;
import com.greedygame.moviebag.repository.MovieListRepository;
import com.greedygame.moviebag.repository.ReviewRepository;


public class ReviewViewModel extends ViewModel {
    private MutableLiveData<Resource<ReviewResult>> reviewResult;

    public ReviewViewModel(String movie_id) {
        ReviewRepository reviewRepository = new ReviewRepository();
        reviewResult =  reviewRepository.getReview(movie_id);
    }

    public MutableLiveData<Resource<ReviewResult>> getReview() {
        return reviewResult;
    }

}