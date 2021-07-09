package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.Parse;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    public static final String TAG = "GridAdapter";
    public static final String POST = "post";

    private Context mContext;
    private List<Post> mPosts;

    public GridAdapter(Context mContext, List<Post> mPosts) {
        this.mContext = mContext;
        this.mPosts = mPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_grid, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "See posts: " + mPosts.toString());
                Log.i(TAG, "Recycler view position: " + viewHolder.getAdapterPosition());
                Post post = mPosts.get(viewHolder.getAdapterPosition());
                Intent intent = new Intent(mContext, PostDetailsActivity.class);
                intent.putExtra(POST, Parcels.wrap(post));
                mContext.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GridAdapter.ViewHolder holder, int position) {
        Post post = mPosts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvGridImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvGridImage = itemView.findViewById(R.id.ivGridImage);
        }

        public void bind(Post post) {
            ParseFile image = post.getImage();
            if(image != null){
                Glide.with(mContext).load(image.getUrl()).into(mIvGridImage);
            }
        }
    }
}
