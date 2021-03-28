package com.greedygame.moviebag.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.greedygame.moviebag.R;
import com.greedygame.moviebag.model.Movie;
import com.greedygame.moviebag.util.Constant;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NowPlayingMovieAdapter extends PagerAdapter {

    private Context mContext;
    private ClickListenerNowPlayingMovie mClickListenerNowPlayingMovie;
    private ArrayList<Movie> mMovies;

    public NowPlayingMovieAdapter(Context context, ArrayList<Movie> movies, ClickListenerNowPlayingMovie clickListenerNowPlayingMovie) {
        mContext = context;
        mMovies = movies;
        mClickListenerNowPlayingMovie = clickListenerNowPlayingMovie;
    }

    @NotNull
    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.item_now_playing_movie_pager,  null);
        ImageView iv_now_playing_movie = itemView.findViewById(R.id.iv_now_playing_movie);
        TextView tv_now_playing_movie_name = itemView.findViewById(R.id.tv_now_playing_movie_name);
        TextView tv_now_playing_movie_release_date = itemView.findViewById(R.id.tv_now_playing_release_date);

        tv_now_playing_movie_name.setText(mMovies.get(position).getTitle());
        tv_now_playing_movie_release_date.setText(mMovies.get(position).getRelease_date());
        Glide.with(mContext)
                .load(Constant.IMAGE_SITE+ mMovies.get(position).getPoster_path())
                .into(iv_now_playing_movie);

        itemView.setOnClickListener(v -> {
            mClickListenerNowPlayingMovie.onClickNowPlayingMovie(position);});
        collection.addView(itemView);
        return itemView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return Math.min(mMovies.size(), 5);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    public interface ClickListenerNowPlayingMovie {
        void onClickNowPlayingMovie(int position);
    }
}
