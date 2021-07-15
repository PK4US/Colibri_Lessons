package com.pk4u.colibri_lessons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.pk4u.colibri_lessons.adapter.TweetAdapter;
import com.pk4u.colibri_lessons.pojo.Tweet;
import com.pk4u.colibri_lessons.pojo.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    private ImageView userImageView;
    private TextView nameTextView;
    private TextView nickTextView;
    private TextView descriptionTextView;
    private TextView locationTextView;
    private TextView followingTextView;
    private TextView followersTextView;

    private RecyclerView tweetsRecyclerView;
    private TweetAdapter tweetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userImageView = findViewById(R.id.userImageView);

        nameTextView = findViewById(R.id.userNameTextView);
        nickTextView = findViewById(R.id.userNickTextView);
        descriptionTextView = findViewById(R.id.userDescriptionTextView);
        locationTextView = findViewById(R.id.userLocationTextView);
        followingTextView = findViewById(R.id.userFollowingCountTextView);
        followersTextView = findViewById(R.id.userFollowersCountTextView);

        loadUserInfo();
        initRecyclerView();
        loadTweets();
    }

    private Collection<Tweet> getTweets() {
        return Arrays.asList(
                new Tweet(getUser(), 1L, "Thu Dec 13 07:31:08 +0000 2017", "Очень длинное описание твита 1",
                        4L, 4L, "https://www.w3schools.com/w3css/img_fjords.jpg"),

                new Tweet(getUser(), 2L, "Thu Dec 12 07:31:08 +0000 2017", "Очень длинное описание твита 2",
                        5L, 5L, "https://www.w3schools.com/w3images/lights.jpg"),

                new Tweet(getUser(), 3L, "Thu Dec 11 07:31:08 +0000 2017", "Очень длинное описание твита 3",
                        6L, 6L, "https://www.w3schools.com/css/img_mountains.jpg")
        );
    }

    private void loadTweets() {
        Collection<Tweet> tweets = getTweets();
        tweetAdapter.setItems(tweets);
    }

    private void initRecyclerView(){
        tweetsRecyclerView = findViewById(R.id.tweetsRecyclerView);
        tweetsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tweetAdapter = new TweetAdapter();
        tweetsRecyclerView.setAdapter(tweetAdapter);
    }

    private void displayUserInfo(User user){
        Picasso.with(this).load(getUser().getImageUrl()).into(userImageView);
        nameTextView.setText(getUser().getName());
        nickTextView.setText(getUser().getNick());
        descriptionTextView.setText(getUser().getDescription());
        locationTextView.setText(getUser().getLocation());
        String followingCount = String.valueOf(getUser().getFollowingCount());
        followingTextView.setText(followingCount);
        String followerCount = String.valueOf(getUser().getFollowersCount());
        followersTextView.setText(followerCount);
    }

    private void loadUserInfo(){
        User user = getUser();
        displayUserInfo(user);
    }

    private User getUser(){
        return new User(1L,"http://i.imgur.com/DvpvklR.png","DevColibry","@devcolibri","Sample text","USA",54,56);
    }
}