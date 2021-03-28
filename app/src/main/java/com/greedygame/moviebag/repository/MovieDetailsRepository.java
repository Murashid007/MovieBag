package com.greedygame.moviebag.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.multidex.BuildConfig;

import com.greedygame.moviebag.model.Movie;
import com.greedygame.moviebag.model.Resource;
import com.greedygame.moviebag.model.MovieResult;
import com.greedygame.moviebag.util.Application;
import com.greedygame.moviebag.util.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsRepository {

    public MutableLiveData<Resource<Movie>> getMovieDetails(String movieId) {
        final MutableLiveData<Resource<Movie>> moviewDetails = new MutableLiveData<>();

        Call<Movie> call  = Application.getInstance().getNetworkService()
                .getMovieDetails( movieId, Constant.SERVER_API_KEY);

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie body = response.body();
                if (body != null) {
                    moviewDetails.setValue(Resource.success(body));
                } else {
                    moviewDetails.setValue(Resource.<Movie>error("No Data", null));
                }
            }
            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                moviewDetails.setValue(Resource.<Movie>error(t.getMessage(),null));
            }
        });
        return moviewDetails;
    }

    public MutableLiveData<Resource<MovieResult>> getSimilarMovieResult(String movieId) {
        final MutableLiveData<Resource<MovieResult>> similarMovie = new MutableLiveData<>();

        Call<MovieResult> call  = Application.getInstance().getNetworkService()
                .getSimilarMovie( movieId, Constant.SERVER_API_KEY);

        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                MovieResult body = response.body();
                if (body != null) {
                    similarMovie.setValue(Resource.success(body));
                } else {
                    similarMovie.setValue(Resource.<MovieResult>error("No Data", null));
                }
            }
            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                similarMovie.setValue(Resource.<MovieResult>error(t.getMessage(),null));
            }
        });
        return similarMovie;
    }


}
