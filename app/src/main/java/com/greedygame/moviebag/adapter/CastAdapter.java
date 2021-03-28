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
import com.greedygame.moviebag.model.Cast;
import com.greedygame.moviebag.model.SimilarMovieProduction;
import com.greedygame.moviebag.util.Constant;

import java.util.ArrayList;


public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Cast> mCasts;

    private RequestOptions mRequestOptions;

    public CastAdapter(Context context, ArrayList<Cast> cast) {
        mContext = context;
        mCasts = cast;
        mRequestOptions = new RequestOptions();
        mRequestOptions = mRequestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_cast, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_name.setText(mCasts.get(position).getName());
        holder.tv_character.setText(mCasts.get(position).getCharacter());

        Glide.with(mContext)
                .load(Constant.IMAGE_SITE+ mCasts.get(position).getProfile_path())
                .centerCrop()
                .apply(mRequestOptions)
                .into(holder.iv_cast);
    }

    @Override
    public int getItemCount() {
        return mCasts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name, tv_character;
        ImageView iv_cast;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_cast = itemView.findViewById(R.id.iv_cast);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_character = itemView.findViewById(R.id.tv_character);

        }

    }

}
