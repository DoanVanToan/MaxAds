package com.example.max_ads.interf;


import android.app.Activity;

public interface InterstitialAdvertisement {
    InterstitialAdvertisement load(Activity activity, InterstitialAdCallback callback);

    boolean isLoaded();

    void show(Activity activity, InterstitialContentCallback callback);
}