package com.example.instagram.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.instagram.GridAdapter;
import com.example.instagram.Post;
import com.example.instagram.R;
import com.example.instagram.databinding.FragmentComposeBinding;
import com.example.instagram.databinding.FragmentProfileBinding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    public static final String TAG = "ProfileFragment";
    public static final String PROFILE_PIC = "profilePic";
    public static final String FOLLOWERS = "Followers";
    public static final String FOLLOWING = "Following";
    public static final int LIMIT = 20;
    public static final int GRID_COUNT = 3;
    public int TOTAL_POSTS;

    private FragmentProfileBinding mBinding;
    GridLayoutManager mGridLayoutManager;
    GridAdapter mAdapter;


    protected List<Post> mAllPosts;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentProfileBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAllPosts = new ArrayList<>();
        mAdapter = new GridAdapter(getContext(), mAllPosts);

        mGridLayoutManager = new GridLayoutManager(getContext(), GRID_COUNT);
        mBinding.rvPosts.setAdapter(mAdapter);
        mBinding.rvPosts.setLayoutManager(mGridLayoutManager);

        mBinding.tvProfileUser.setText(ParseUser.getCurrentUser().getUsername());
        mBinding.tvFollowers.setText(ParseUser.getCurrentUser().getString(FOLLOWERS));
        mBinding.tvFollowing.setText(ParseUser.getCurrentUser().getString(FOLLOWING));

        Log.i(TAG, "Number of posts: " + mAllPosts.size());

        ParseFile profilePic = ParseUser.getCurrentUser().getParseFile(PROFILE_PIC);
        if(profilePic != null){
            Glide.with(this).load(profilePic.getUrl()).circleCrop().into(mBinding.ivProfilePicture);
        }

        queryPosts();
//        mBinding.tvNumPosts.setText(String.valueOf(mAllPosts.size()));
    }

    private void queryPosts() {
        // specify what type of data we want to query - Post.class
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        // include data referred by user key
        query.include(Post.KEY_USER);
        // limit query to latest 20 items
        query.setLimit(LIMIT);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        // order posts by creation date (newest first)
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        // start an asynchronous call for posts
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                // check for errors
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }

                // save received posts to list and notify adapter of new data
                mAllPosts.addAll(posts);
                TOTAL_POSTS = mAllPosts.size();
                mBinding.tvNumPosts.setText(String.valueOf(mAllPosts.size()));
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}