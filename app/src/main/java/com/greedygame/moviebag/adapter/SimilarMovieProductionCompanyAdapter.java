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
import com.greedygame.moviebag.model.SimilarMovieProduction;
import com.greedygame.moviebag.util.Constant;

import java.util.ArrayList;


public class SimilarMovieProductionCompanyAdapter extends RecyclerView.Adapter<SimilarMovieProductionCompanyAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<SimilarMovieProduction> mDatas;

    private RequestOptions mRequestOptions;
    private ClickListener mClickListener;

    public SimilarMovieProductionCompanyAdapter(Context context, ArrayList<SimilarMovieProduction> datas, ClickListener clickListener) {
        mContext = context;
        mDatas = datas;
        mClickListener = clickListener;
        mRequestOptions = new RequestOptions();
        mRequestOptions = mRequestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_similar_movie_production, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_name.setText(mDatas.get(position).getName());

        Glide.with(mContext)
                .load(Constant.IMAGE_SITE+ mDatas.get(position).getLogo())
                .centerCrop()
                .error(R.mipmap.ic_launcher_round)
                .apply(mRequestOptions)
                .into(holder.iv_logo);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_name;
        ImageView iv_logo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_logo = itemView.findViewById(R.id.iv_logo);
            tv_name = itemView.findViewById(R.id.tv_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mClickListener != null) {
                mClickListener.onClickSimilarMovie(getAdapterPosition());
            }
        }
    }

    public interface ClickListener {
        void onClickSimilarMovie(int position);
    }
}
