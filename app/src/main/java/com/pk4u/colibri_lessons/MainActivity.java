package com.pk4u.colibri_lessons;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.pk4u.colibri_lessons.pojo.User;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView userImageView;
    private TextView nameTextView;
    private TextView nickTextView;
    private TextView descriptionTextView;
    private TextView locationTextView;
    private TextView followingTextView;
    private TextView followersTextView;

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