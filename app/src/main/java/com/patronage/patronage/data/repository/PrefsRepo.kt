package com.patronage.patronage.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefsRepo @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        private val PREF_PREGUNTAS_LEIDAS = intPreferencesKey("preguntas_leidas")
    }

    val readPreguntasLeidas: Flow<Int> = dataStore.data.map { preferences ->
        preferences[PREF_PREGUNTAS_LEIDAS] ?: 0
    }

    suspend fun writePreguntasLeidas(value: Int) {
        dataStore.edit { preferences ->
            preferences[PREF_PREGUNTAS_LEIDAS] = value
        }
    }
}