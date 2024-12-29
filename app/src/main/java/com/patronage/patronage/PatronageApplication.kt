package com.patronage.patronage

import android.app.Application
import android.content.res.Configuration
import android.util.Log

class PatronageApplication : Application() {
    val TAG = "PatronageApplication"
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "PatronageApplication onCreate")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        //Log.d(TAG, "PatronageApplication ${newConfig.orientation}")
        val orientation = if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            "Landscape"
        } else {
            "Portrait"
        }
        Log.d("PatronageApplication", "Cambio de orientación: $orientation")
    }

    override fun onTerminate() {
        Log.d(TAG, "PatronageApplication onTerminate")
        super.onTerminate()
    }

    override fun onLowMemory() {
        Log.d(TAG, "PatronageApplication onLowMemory")
        super.onLowMemory()
    }
}