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
import com.greedygame.moviebag.adapter.SimilarMovieProductionCompanyAdapter;
import com.greedygame.moviebag.model.Cast;
import com.greedygame.moviebag.model.Movie;
import com.greedygame.moviebag.model.SimilarMovieProduction;
import com.greedygame.moviebag.util.Constant;
import com.greedygame.moviebag.viewmodel.CastViewModel;
import com.greedygame.moviebag.viewmodel.MovieDetailsViewModel;
import com.greedygame.moviebag.viewmodel.ViewModelFactory;

import java.util.ArrayList;

public class CastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast);

        init();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Cast> casts = new ArrayList<>();
        CastAdapter castAdapter = new CastAdapter(this, casts);
        RecyclerView rv_cast = findViewById(R.id.rv_cast);
        rv_cast.setLayoutManager(new GridLayoutManager(this, 2));
        rv_cast.setAdapter(castAdapter);

        CastViewModel mViewModel = ViewModelProviders.of(this, new ViewModelFactory(getIntent().getStringExtra(Constant.MOVIE_ID), ViewModelFactory.Type.CAST)).get(CastViewModel.class);

        mViewModel.getCast().observe(this, castResource -> {
            findViewById(R.id.progressBar).setVisibility(View.GONE);
            switch (castResource.status)
            {
                case SUCCESS:
                    if (castResource.data != null) {
                        casts.addAll(castResource.data.getCast());
                        castAdapter.notifyDataSetChanged();
                    }
                    break;

                case ERROR:
                    if (castResource.data != null) {
                        Toast.makeText(this, castResource.data.toString(),Toast.LENGTH_SHORT).show();
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