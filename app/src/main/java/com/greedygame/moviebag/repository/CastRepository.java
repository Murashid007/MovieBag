package com.greedygame.moviebag.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.multidex.BuildConfig;


import com.greedygame.moviebag.model.CastResult;
import com.greedygame.moviebag.model.Resource;

import com.greedygame.moviebag.util.Application;
import com.greedygame.moviebag.util.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CastRepository {

    public MutableLiveData<Resource<CastResult>> getCast(String movieId) {
        final MutableLiveData<Resource<CastResult>> castResult = new MutableLiveData<>();

        Call<CastResult> call  = Application.getInstance().getNetworkService()
                .getCredits( movieId, Constant.SERVER_API_KEY);

        call.enqueue(new Callback<CastResult>() {
            @Override
            public void onResponse(Call<CastResult> call, Response<CastResult> response) {
                CastResult body = response.body();
                if (body != null) {
                    castResult.setValue(Resource.success(body));
                } else {
                    castResult.setValue(Resource.<CastResult>error("No Data", null));
                }
            }
            @Override
            public void onFailure(Call<CastResult> call, Throwable t) {
                castResult.setValue(Resource.<CastResult>error(t.getMessage(),null));
            }
        });
        return castResult;
    }

}
