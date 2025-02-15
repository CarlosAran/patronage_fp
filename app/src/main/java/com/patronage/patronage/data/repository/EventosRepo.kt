package com.patronage.patronage.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EventosRepo @Inject constructor(
    private val eventoDao: EventoDao,
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.dataStore

    suspend fun insertarEvento(evento: EventoBean) {
        eventoDao.insertaEvento(evento)
    }

    suspend fun getRandomEvento(): EventoBean? {
        return eventoDao.getRandomEvento()
    }

    suspend fun marcarEventoLeido(id: Int) {
        eventoDao.marcarEventoLeido(id)
    }

    suspend fun resetEventos() {
        eventoDao.resetEventos()
    }

    private val LAST_EVENT_KEY = stringPreferencesKey("last_event")

    // Get all events from DB
    suspend fun getAllEventos(): List<EventoBean> {
        return eventoDao.getAllEventos()
    }

    // Save last event in DataStore
    suspend fun saveLastEvent(event: String) {
        dataStore.edit { preferences ->
            preferences[LAST_EVENT_KEY] = event
        }
    }

    // Read last event from DataStore
    fun getLastEvent(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[LAST_EVENT_KEY]
        }
    }
}
