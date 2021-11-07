package com.example.max_ads.interf;


public interface InterstitialContentCallback {
    void onAdFailedToShowFullScreenContent(String error);

    void onAdShowedFullScreenContent();

    void onAdDismissedFullScreenContent();
}
