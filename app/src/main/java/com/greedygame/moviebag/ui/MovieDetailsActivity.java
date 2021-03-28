package com.greedygame.moviebag.ui;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.greedygame.moviebag.R;
import com.greedygame.moviebag.adapter.SimilarMovieProductionCompanyAdapter;
import com.greedygame.moviebag.model.Movie;
import com.greedygame.moviebag.model.ProductionCompanies;
import com.greedygame.moviebag.model.SimilarMovieProduction;
import com.greedygame.moviebag.util.Constant;
import com.greedygame.moviebag.viewmodel.MovieDetailsViewModel;
import com.greedygame.moviebag.viewmodel.ViewModelFactory;

import java.util.ArrayList;


public class MovieDetailsActivity extends AppCompatActivity implements SimilarMovieProductionCompanyAdapter.ClickListener {

    private ArrayList<SimilarMovieProduction> mSimilarMovie;
    private String mMoviesId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        initView();
    }

    private void initView() {
        ImageView iv_movie = findViewById(R.id.iv_movie);
        TextView tv_title = findViewById(R.id.tv_title);
        TextView tv_vote_average = findViewById(R.id.tv_vote_average);
        TextView tv_language = findViewById(R.id.tv_language);
        TextView tv_popularity = findViewById(R.id.tv_popularity);
        TextView tv_release_date = findViewById(R.id.tv_release_date);
        TextView tv_release_status = findViewById(R.id.tv_release_status);
        TextView tv_overview = findViewById(R.id.tv_overview);
        findViewById(R.id.tv_casts).setOnClickListener(v -> { openActivity(true);});
        findViewById(R.id.tv_reviews).setOnClickListener(v -> { openActivity(false);});
        findViewById(R.id.iv_back_button).setOnClickListener(v -> { finish();});

        RecyclerView rv_production_companies = findViewById(R.id.rv_production_companies);
        ArrayList<SimilarMovieProduction> productions = new ArrayList<>();
        SimilarMovieProductionCompanyAdapter productionsAdapter = new SimilarMovieProductionCompanyAdapter(this, productions, null);
        rv_production_companies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv_production_companies.setAdapter(productionsAdapter);

        mSimilarMovie = new ArrayList<>();
        SimilarMovieProductionCompanyAdapter similarMovieAdapter = new SimilarMovieProductionCompanyAdapter(this, mSimilarMovie, this);
        RecyclerView rv_similar_movies = findViewById(R.id.rv_similar_movies);
        rv_similar_movies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv_similar_movies.setAdapter(similarMovieAdapter);

        MovieDetailsViewModel mViewModel = ViewModelProviders.of(this, new ViewModelFactory(getIntent().getStringExtra(Constant.MOVIE_ID), ViewModelFactory.Type.MOVIE_DETAILS)).get(MovieDetailsViewModel.class);

        mViewModel.getMovieDetails().observe(this, movieResource -> {
            findViewById(R.id.progressBar).setVisibility(View.GONE);
            switch (movieResource.status)
            {
                case SUCCESS:
                    if (movieResource.data != null) {
                        Movie movie = movieResource.data;
                        mMoviesId = movie.getId();
                        tv_title.setText(movie.getTitle());
                        tv_vote_average.setText(getString(R.string.vote) + movie.getVote_average());
                        tv_language.setText(getString(R.string.language) + movie.getOriginal_language());
                        tv_popularity.setText(getString(R.string.popularity) + movie.getPopularity());
                        tv_release_date.setText(getString(R.string.release_date) + movie.getRelease_date());
                        tv_release_status.setText(getString(R.string.release_status) +movie.getStatus());
                        tv_overview.setText(movie.getOverview());

                        RequestOptions requestOptions = new RequestOptions();
                        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
                        Glide.with(this)
                                .load(Constant.IMAGE_SITE+ movie.getPoster_path())
                                .apply(requestOptions)
                                .into(iv_movie);

                        for(ProductionCompanies productionCompanies : movie.getProduction_companies()) {
                            SimilarMovieProduction similarMovieProduction = new SimilarMovieProduction(productionCompanies.getId(), productionCompanies.getName(), productionCompanies.getLogo_path());
                            productions.add(similarMovieProduction);
                        }
                        productionsAdapter.notifyDataSetChanged();
                    }
                    break;

                case ERROR:
                    if (movieResource.data != null) {
                        Toast.makeText(this, movieResource.data.toString(),Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        });

        mViewModel.getSimilarMovie().observe(this, similarMovieResource -> {
            switch (similarMovieResource.status)
            {
                case SUCCESS:
                    if (similarMovieResource.data != null) {
                        for(Movie movie : similarMovieResource.data.getResults()) {
                            SimilarMovieProduction similarMovieProduction = new SimilarMovieProduction(movie.getId(), movie.getTitle(), movie.getPoster_path());
                            mSimilarMovie.add(similarMovieProduction);
                        }
                        similarMovieAdapter.notifyDataSetChanged();
                    }
                    break;

                case ERROR:
                    if (similarMovieResource.data != null) {
                        Toast.makeText(this, similarMovieResource.data.toString(),Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        });
    }

    private void openActivity(boolean isCast) {
        Intent intent;
        if (isCast) {
            intent = new Intent(this, CastActivity.class);
        } else {
            intent = new Intent(this, ReviewActivity.class);
        }
        intent.putExtra(Constant.MOVIE_ID, mMoviesId);
        startActivity(intent);
    }

    @Override
    public void onClickSimilarMovie(int position) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);;
        intent.putExtra(Constant.MOVIE_ID, mSimilarMovie.get(position).getId());
        startActivity(intent);
        finish();
    }
}