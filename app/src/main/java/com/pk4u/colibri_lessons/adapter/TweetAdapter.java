package com.pk4u.colibri_lessons.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pk4u.colibri_lessons.R;
import com.pk4u.colibri_lessons.pojo.Tweet;
import com.squareup.picasso.Picasso;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.TweetViewHolder>{

    private static final String TWITTER_RESPONSE_FORMAT = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
    private static final String MONTH_DAY_FORMAT = "MMM d";

    private List<Tweet> tweetList = new ArrayList<>();

    class TweetViewHolder extends RecyclerView.ViewHolder{
        private ImageView userImageView;
        private TextView nameTextView;
        private TextView nickTextView;
        private TextView createDateTextView;
        private TextView contentTextView;
        private ImageView tweetImageView;
        private TextView retweetsTextView;
        private TextView likeTextView;

        public TweetViewHolder(View itemView) {
            super(itemView);
            userImageView = itemView.findViewById(R.id.profileImageView);
            nameTextView = itemView.findViewById(R.id.authorNameTextView);
            nickTextView = itemView.findViewById(R.id.authorNickTextView);
            createDateTextView = itemView.findViewById(R.id.authorDateTextView);
            contentTextView = itemView.findViewById(R.id.tweetContentTextView);
            tweetImageView = itemView.findViewById(R.id.tweetImageView);
            retweetsTextView = itemView.findViewById(R.id.retweetsTextView);
            likeTextView = itemView.findViewById(R.id.likesTextView);
        }
        public void bind(Tweet tweet){
            nameTextView.setText(tweet.getUser().getName());
            nickTextView.setText(tweet.getUser().getNick());
            contentTextView.setText(tweet.getText());
            retweetsTextView.setText(String.valueOf(tweet.getRetweetCount()));
            likeTextView.setText(String.valueOf(tweet.getFavouriteCount()));

            String creationDateFormatted = getFormattedDate(tweet.getCreationDate());
            createDateTextView.setText(creationDateFormatted);

            Picasso.with(itemView.getContext()).load(tweet.getUser().getImageUrl()).into(userImageView);

            String tweetPhotoUrl = tweet.getImageUrl();
            Picasso.with(itemView.getContext()).load(tweetPhotoUrl).into(tweetImageView);

            tweetImageView.setVisibility(tweetPhotoUrl != null ? View.VISIBLE : View.GONE);
        }
    }

    private String getFormattedDate(String rawDate) {
        SimpleDateFormat utcFormat = new SimpleDateFormat(TWITTER_RESPONSE_FORMAT, Locale.ROOT);
        SimpleDateFormat dispayedFormat = new SimpleDateFormat(MONTH_DAY_FORMAT, Locale.getDefault());

        try {
            Date date = utcFormat.parse(rawDate);
            return dispayedFormat.format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void setItems(Collection<Tweet>tweets){
        tweetList.addAll(tweets);
        notifyDataSetChanged();
    }

    public void clearItems(){
        tweetList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_item_view, parent, false);
        return new TweetViewHolder(view);
    }

    public void onBindViewHolder(TweetViewHolder holder, int position) {
        holder.bind(tweetList.get(position));
    }

    public int getItemCount() {
        return tweetList.size();
    }
}