package com.greedygame.moviebag.ui;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.greedygame.moviebag.R;
import com.greedygame.moviebag.adapter.CastAdapter;
import com.greedygame.moviebag.adapter.ReviewAdapter;
import com.greedygame.moviebag.model.Cast;
import com.greedygame.moviebag.model.Review;
import com.greedygame.moviebag.util.Constant;
import com.greedygame.moviebag.viewmodel.CastViewModel;
import com.greedygame.moviebag.viewmodel.ReviewViewModel;
import com.greedygame.moviebag.viewmodel.ViewModelFactory;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        init();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Review> reviews = new ArrayList<>();
        ReviewAdapter reviewAdapter = new ReviewAdapter(this, reviews);
        RecyclerView rv_review = findViewById(R.id.rv_review);
        rv_review.setLayoutManager(new LinearLayoutManager(this));
        rv_review.setAdapter(reviewAdapter);

        ReviewViewModel mViewModel = ViewModelProviders.of(this, new ViewModelFactory(getIntent().getStringExtra(Constant.MOVIE_ID), ViewModelFactory.Type.REVIEW)).get(ReviewViewModel.class);

        mViewModel.getReview().observe(this, reviewResource -> {
            findViewById(R.id.progressBar).setVisibility(View.GONE);
            switch (reviewResource.status)
            {
                case SUCCESS:
                    if (reviewResource.data != null) {
                        reviews.addAll(reviewResource.data.getResults());
                        reviewAdapter.notifyDataSetChanged();
                    }
                    break;

                case ERROR:
                    if (reviewResource.data != null) {
                        Toast.makeText(this, reviewResource.data.toString(),Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}