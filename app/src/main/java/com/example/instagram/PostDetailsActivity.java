package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.instagram.databinding.ActivityPostDetailsBinding;

import org.parceler.Parcels;

import java.util.Date;

public class PostDetailsActivity extends AppCompatActivity {

    public static final String POST = "post";
    Post mPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPostDetailsBinding binding = ActivityPostDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        mPost = Parcels.unwrap(intent.getParcelableExtra(POST));

        binding.tvPostUsername.setText(mPost.getUser().getUsername());
        binding.tvDescriptionUser.setText(mPost.getUser().getUsername());
        binding.tvPostDescription.setText(mPost.getDescription());
        Glide.with(this).load(mPost.getImage().getUrl()).into(binding.ivPost);

        Date createdAt = mPost.getCreatedAt();
        String timeAgo = Post.calculateTimeAgo(createdAt);
        binding.tvCreatedAt.setText(timeAgo);
    }
}