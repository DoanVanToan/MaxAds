package com.example.max_ads.interf;


public interface InterstitialAdCallback {
    void onAdLoaded(InterstitialAdvertisement interstitialAds);

    void onAdFailedToLoad(String error);
}
