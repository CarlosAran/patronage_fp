package com.patronage.patronage.data

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

// Singleton DataStore instance
val Context.dataStore by preferencesDataStore(name = "app_preferences")
