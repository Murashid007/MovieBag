package com.greedygame.moviebag.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.multidex.BuildConfig;


import com.greedygame.moviebag.model.Resource;
import com.greedygame.moviebag.model.ReviewResult;
import com.greedygame.moviebag.util.Application;
import com.greedygame.moviebag.util.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewRepository {

    public MutableLiveData<Resource<ReviewResult>> getReview(String movieId) {
        final MutableLiveData<Resource<ReviewResult>> reviewResult = new MutableLiveData<>();

        Call<ReviewResult> call  = Application.getInstance().getNetworkService()
                .getReview( movieId, Constant.SERVER_API_KEY);

        call.enqueue(new Callback<ReviewResult>() {
            @Override
            public void onResponse(Call<ReviewResult> call, Response<ReviewResult> response) {
                ReviewResult body = response.body();
                if (body != null) {
                    reviewResult.setValue(Resource.success(body));
                } else {
                    reviewResult.setValue(Resource.<ReviewResult>error("No Data", null));
                }
            }
            @Override
            public void onFailure(Call<ReviewResult> call, Throwable t) {
                reviewResult.setValue(Resource.<ReviewResult>error(t.getMessage(),null));
            }
        });
        return reviewResult;
    }

}
