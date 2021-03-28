package com.greedygame.moviebag.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.greedygame.moviebag.model.Resource;
import com.greedygame.moviebag.model.MovieResult;
import com.greedygame.moviebag.util.Application;
import com.greedygame.moviebag.util.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListRepository {

    public MutableLiveData<Resource<MovieResult>> getNowPlayingMovie() {
        final MutableLiveData<Resource<MovieResult>> movieResult = new MutableLiveData<>();

        Call<MovieResult> call  = Application.getInstance().getNetworkService().getNowPlayingMovie(Constant.SERVER_API_KEY);

        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                MovieResult body = response.body();
                Log.d(Constant.TAG, "onResponse: now Playing movie "+body);
                if (body != null) {
                    movieResult.setValue(Resource.success(body));
                } else {
                    movieResult.setValue(Resource.<MovieResult>error("No Data", null));
                }
            }
            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Log.d(Constant.TAG, "onFailure: now playing movie "+t.getMessage());
                movieResult.setValue(Resource.<MovieResult>error(t.getMessage(),null));
            }
        });
        return movieResult;
    }

    public MutableLiveData<Resource<MovieResult>> getPopularMovieResult() {
        final MutableLiveData<Resource<MovieResult>> moviewResult = new MutableLiveData<>();

        Call<MovieResult> call  = Application.getInstance().getNetworkService()
                .getPopularMovie( Constant.SERVER_API_KEY);

        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                MovieResult body = response.body();
                Log.d(Constant.TAG, "onResponse: popular moview "+body);
                Log.d(Constant.TAG, "onResponse: popular Url "+response.raw().request().url());

                if (body != null) {
                    moviewResult.setValue(Resource.success(body));
                } else {
                    moviewResult.setValue(Resource.<MovieResult>error("No Data", null));
                }
            }
            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                moviewResult.setValue(Resource.<MovieResult>error(t.getMessage(),null));
            }
        });
        return moviewResult;
    }


}
