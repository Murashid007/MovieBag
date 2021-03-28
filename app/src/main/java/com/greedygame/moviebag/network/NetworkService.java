package com.greedygame.moviebag.network;



import com.greedygame.moviebag.model.CastResult;
import com.greedygame.moviebag.model.Movie;
import com.greedygame.moviebag.model.MovieResult;
import com.greedygame.moviebag.model.ReviewResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkService {

    @GET("3/movie/now_playing")
    Call<MovieResult> getNowPlayingMovie(@Query("api_key") String apiKey);

    @GET("3/movie/popular")
    Call<MovieResult> getPopularMovie(@Query("api_key") String apiKey);

    @GET("3/movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id") String movie_id, @Query("api_key") String apiKey);

    @GET("3/movie/{movie_id}/similar")
    Call<MovieResult> getSimilarMovie(@Path("movie_id") String movie_id, @Query("api_key") String apiKey);

    @GET("3/movie/{movie_id}/reviews")
    Call<ReviewResult> getReview(@Path("movie_id") String movie_id, @Query("api_key") String apiKey);

    @GET("3/movie/{movie_id}/credits")
    Call<CastResult> getCredits(@Path("movie_id") String movie_id, @Query("api_key") String apiKey);

}