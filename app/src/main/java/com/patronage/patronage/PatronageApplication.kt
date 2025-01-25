package com.patronage.patronage

import android.app.Application
import android.content.res.Configuration
import android.util.Log
import androidx.room.Room
import com.patronage.patronage.data.AppDB

class PatronageApplication : Application() {
    val TAG = "PatronageApplication"
    override fun onCreate() {
        super.onCreate()
    }

    val database: AppDB by lazy {
        Room.databaseBuilder(
            this,
            AppDB::class.java,
            "PatronageDB-Room"
        ).addMigrations(AppDB.MIGRATION_2_3)
            .build()
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
        database.close()
    }

    override fun onLowMemory() {
        Log.d(TAG, "PatronageApplication onLowMemory")
        super.onLowMemory()
    }
}