package com.greedygame.moviebag.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.greedygame.moviebag.R;
import com.greedygame.moviebag.adapter.PopularMovieAdapter;
import com.greedygame.moviebag.adapter.NowPlayingMovieAdapter;
import com.greedygame.moviebag.model.Movie;
import com.greedygame.moviebag.util.Constant;
import com.greedygame.moviebag.util.ViewPagerCustomTransformer;
import com.greedygame.moviebag.viewmodel.MovieListViewModel;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity implements PopularMovieAdapter.ClickListenerPopularMovie, NowPlayingMovieAdapter.ClickListenerNowPlayingMovie {

    private ArrayList<Movie> mNowPlayingMovie, mPopularMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        init();
    }

    private void init() {
        MovieListViewModel mViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        mNowPlayingMovie = new ArrayList<>();
        NowPlayingMovieAdapter nowPlayingMovieAdapter = new NowPlayingMovieAdapter(this, mNowPlayingMovie,this);
        ViewPager vp_now_playing_movies = findViewById(R.id.vp_now_playing_movies);
        vp_now_playing_movies.setAdapter(nowPlayingMovieAdapter);
        TabLayout tb_now_playing_movies = findViewById(R.id.tb_now_playing_movies);
        vp_now_playing_movies.setPageTransformer(true, new ViewPagerCustomTransformer());
        tb_now_playing_movies.setupWithViewPager(vp_now_playing_movies);

        mPopularMovies = new ArrayList<>();
        PopularMovieAdapter popularMovieAdapter = new PopularMovieAdapter(this, mPopularMovies, this);
        RecyclerView rv_popular_movies = findViewById(R.id.rv_popular_movies);
        rv_popular_movies.setLayoutManager(new LinearLayoutManager(this));
        rv_popular_movies.setAdapter(popularMovieAdapter);

        mViewModel.getNowplayingMovie().observe(this, nowPlayingMovieResource -> {
            findViewById(R.id.progressBar).setVisibility(View.GONE);
            switch (nowPlayingMovieResource.status)
            {
                case SUCCESS:
                    if (nowPlayingMovieResource.data != null) {
                        mNowPlayingMovie.addAll(nowPlayingMovieResource.data.getResults());
                        nowPlayingMovieAdapter.notifyDataSetChanged();
                        vp_now_playing_movies.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (vp_now_playing_movies.getCurrentItem() + 1 < Math.min(mNowPlayingMovie.size(), 5)) {
                                    vp_now_playing_movies.setCurrentItem(vp_now_playing_movies.getCurrentItem() + 1, true);
                                } else {
                                    vp_now_playing_movies.setCurrentItem(0, true);
                                }
                                vp_now_playing_movies.postDelayed(this, Constant.VIEW_PAGER_TIMER);
                            }
                        }, Constant.VIEW_PAGER_TIMER);
                    }
                    break;

                case ERROR:
                    if (nowPlayingMovieResource.data != null) {
                        Toast.makeText(this, nowPlayingMovieResource.data.toString(),Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        });

        mViewModel.getPopularMovieResult().observe(this, popularMoviewResultResource -> {
            switch (popularMoviewResultResource.status)
            {
                case SUCCESS:
                    if (popularMoviewResultResource.data != null) {
                        mPopularMovies.addAll(popularMoviewResultResource.data.getResults());
                        popularMovieAdapter.notifyDataSetChanged();
                    }
                    break;

                case ERROR:
                    if (popularMoviewResultResource.data != null) {
                        Toast.makeText(this, popularMoviewResultResource.data.toString(),Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        });


    }

    @Override
    public void onClickPopularMovie(int position) {
        openMovieDetailsActivity(mPopularMovies.get(position).getId());
    }

    @Override
    public void onClickNowPlayingMovie(int position) {
        openMovieDetailsActivity(mNowPlayingMovie.get(position).getId());
    }

    private void openMovieDetailsActivity(String movieId) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(Constant.MOVIE_ID, movieId);
        startActivity(intent);
    }
}