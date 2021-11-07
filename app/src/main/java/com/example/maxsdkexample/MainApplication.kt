package com.example.maxsdkexample

import android.app.Application
import com.example.max_ads.max.MaxConfig

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        MaxConfig.initialize(this)
    }
}