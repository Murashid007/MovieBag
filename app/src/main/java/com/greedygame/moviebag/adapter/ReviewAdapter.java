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
import com.greedygame.moviebag.model.Review;
import com.greedygame.moviebag.util.Constant;

import java.util.ArrayList;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Review> mReview;

    private RequestOptions mRequestOptions;

    public ReviewAdapter(Context context, ArrayList<Review> reviews) {
        mContext = context;
        mReview = reviews;
        mRequestOptions = new RequestOptions();
        mRequestOptions = mRequestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_review, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_author_name.setText(mReview.get(position).getAuthor_details().getName());
        holder.tv_date.setText(mReview.get(position).getCreated_at().substring(0, 10));
        holder.tv_content.setText(mReview.get(position).getContent());

        Glide.with(mContext)
                .load(Constant.IMAGE_SITE+ mReview.get(position).getAuthor_details().getAvatar_path())
                .error(R.drawable.review_author)
                .apply(mRequestOptions)
                .into(holder.iv_author);
    }

    @Override
    public int getItemCount() {
        return mReview.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_author_name, tv_date, tv_content;
        ImageView iv_author;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_author = itemView.findViewById(R.id.iv_author);
            tv_author_name = itemView.findViewById(R.id.tv_author_name);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_content = itemView.findViewById(R.id.tv_content);
        }

    }

}
