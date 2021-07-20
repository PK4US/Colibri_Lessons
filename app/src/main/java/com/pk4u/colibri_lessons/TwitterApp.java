package com.pk4u.colibri_lessons;

import android.app.Application;

import com.twitter.sdk.android.core.Twitter;

public class TwitterApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Twitter.initialize(this);
    }
}
