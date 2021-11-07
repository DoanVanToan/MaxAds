package com.example.max_ads.max;

import android.app.Application;
import android.util.Log;

import com.applovin.sdk.AppLovinSdk;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MaxConfig {
    private MaxConfig() {
    }

    public static void initialize(Application application) {
        try {
            AppLovinSdk.getInstance(application).setMediationProvider("max");
            AppLovinSdk.initializeSdk(application, configuration -> {
                // AppLovin SDK is initialized, start loading ads
                Log.d("Max", " AppLovin SDK is initialized");
            });
            AppLovinSdk.getInstance(application).getSettings().setTestDeviceAdvertisingIds(
                    new ArrayList<>()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
