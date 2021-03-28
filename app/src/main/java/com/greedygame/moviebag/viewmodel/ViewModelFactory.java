package com.greedygame.moviebag.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.greedygame.moviebag.util.Application;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private String mMovieId;
    private Type mType;

    public ViewModelFactory(String movieId, Type type) {
        mMovieId = movieId;
        mType = type;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        switch (mType) {
            case MOVIE_DETAILS:
                return (T) new MovieDetailsViewModel(mMovieId);
            case CAST:
                return (T) new CastViewModel(mMovieId);
             case REVIEW:
                 return (T) new ReviewViewModel(mMovieId);
        }
        return null;
    }

    public enum Type { MOVIE_DETAILS, CAST, REVIEW }

}