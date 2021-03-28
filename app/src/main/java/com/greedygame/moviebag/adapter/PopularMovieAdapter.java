package com.greedygame.moviebag.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.greedygame.moviebag.R;
import com.greedygame.moviebag.model.Movie;
import com.greedygame.moviebag.util.Constant;

import java.util.ArrayList;


public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Movie> mMovies;

    RequestOptions mRequestOptions;
    ClickListenerPopularMovie mClickListenerPopularMovie;


    public PopularMovieAdapter(Context context, ArrayList<Movie> movies, ClickListenerPopularMovie clickListenerPopularMovie) {
        mContext = context;
        mMovies = movies;
        mClickListenerPopularMovie = clickListenerPopularMovie;
        mRequestOptions = new RequestOptions();
        mRequestOptions = mRequestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_popular_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_title.setText(mMovies.get(position).getTitle());
        holder.tv_vote_average.setText(mContext.getString(R.string.vote) + mMovies.get(position).getVote_average());
        holder.tv_language.setText(mContext.getString(R.string.language) + mMovies.get(position).getOriginal_language());
        holder.tv_release_date.setText(mContext.getString(R.string.release_date) + mMovies.get(position).getRelease_date());

        Glide.with(mContext)
                .load(Constant.IMAGE_SITE+ mMovies.get(position).getPoster_path())
                .apply(mRequestOptions)
                .into(holder.iv_popular_movie);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_title, tv_vote_average, tv_language, tv_release_date;
        ImageView iv_popular_movie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_vote_average = itemView.findViewById(R.id.tv_vote_average);
            tv_language = itemView.findViewById(R.id.tv_language);
            tv_release_date = itemView.findViewById(R.id.tv_release_date);
            iv_popular_movie = itemView.findViewById(R.id.iv_popular_movie);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickListenerPopularMovie.onClickPopularMovie(getAdapterPosition());
        }
    }

    public interface ClickListenerPopularMovie {
        void onClickPopularMovie(int position);
    }
}
