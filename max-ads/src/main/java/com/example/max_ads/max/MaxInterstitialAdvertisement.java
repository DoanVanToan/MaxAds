package com.example.max_ads.max;

import android.app.Activity;
import android.util.Log;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.example.max_ads.interf.InterstitialAdCallback;
import com.example.max_ads.interf.InterstitialAdvertisement;
import com.example.max_ads.interf.InterstitialContentCallback;

public class MaxInterstitialAdvertisement implements InterstitialAdvertisement, MaxAdListener {

    private final String mUnit;
    private MaxInterstitialAd mInterstitialAd;
    private InterstitialAdCallback mCallback;

    public MaxInterstitialAdvertisement(String unit) {
        mUnit = unit;
    }

    public static MaxInterstitialAdvertisement fromUnit(String unit) {
        return new MaxInterstitialAdvertisement(unit);
    }

    public static MaxInterstitialAdvertisement fromConfigKey(String unitKey) {
        return new MaxInterstitialAdvertisement(unitKey);
    }

    @Override
    public InterstitialAdvertisement load(Activity activity, InterstitialAdCallback callback) {
        mCallback = callback;
        if (mUnit != null) {
            Log.d("Max", "MaxInterstitialAdvertisement load: " + mUnit);
            mInterstitialAd = new MaxInterstitialAd(mUnit, activity);
            mInterstitialAd.setListener(this);
            mInterstitialAd.loadAd();
        }

        return this;
    }

    @Override
    public boolean isLoaded() {
        return mInterstitialAd != null && mInterstitialAd.isReady();
    }

    @Override
    public void show(Activity activity, InterstitialContentCallback callback) {
        if (mUnit != null && mInterstitialAd != null) {
            mInterstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    if (ad instanceof MaxInterstitialAd) {
                        mInterstitialAd = (MaxInterstitialAd) ad;
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {
                    Log.d("Max", "MaxInterstitialAdvertisement onAdDisplayed ");
                    callback.onAdShowedFullScreenContent();
                    mInterstitialAd = null;
                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    Log.d("Max", "MaxInterstitialAdvertisement onAdHidden ");
                    if (callback != null) callback.onAdDismissedFullScreenContent();
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    Log.d("Max", "MaxInterstitialAdvertisement onAdLoadFailed ");
                    if (callback != null)
                        callback.onAdFailedToShowFullScreenContent(error.getMessage());
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    Log.d("Max", "MaxInterstitialAdvertisement onAdDisplayFailed ");
                    if (callback != null)
                        callback.onAdFailedToShowFullScreenContent(error.getMessage());
                }
            });
            mInterstitialAd.showAd();
        } else {
            if (callback != null) {
                callback.onAdDismissedFullScreenContent();
            }
        }
    }

    @Override
    public void onAdLoaded(MaxAd ad) {
        if (mCallback != null)
            mCallback.onAdLoaded(MaxInterstitialAdvertisement.this);
        Log.d("Max", "MaxInterstitialAdvertisement onAdLoaded: " + mUnit);
    }

    @Override
    public void onAdDisplayed(MaxAd ad) {

    }

    @Override
    public void onAdHidden(MaxAd ad) {

    }

    @Override
    public void onAdClicked(MaxAd ad) {

    }

    @Override
    public void onAdLoadFailed(String adUnitId, MaxError error) {
        mInterstitialAd = null;
        if (mCallback != null) mCallback.onAdFailedToLoad(error.getMessage());
        Log.d("Max", "MaxInterstitialAdvertisement onAdFailedToLoad: " + error.getMessage());
    }

    @Override
    public void onAdDisplayFailed(MaxAd ad, MaxError error) {
    }
}
