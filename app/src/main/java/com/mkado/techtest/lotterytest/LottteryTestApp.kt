package com.mkado.techtest.lotterytest

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LotteryTestApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Log.d("LotteryTestApp", "app created")
    }
}