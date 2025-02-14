package com.patronage.patronage.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.patronage.patronage.data.network.ChuckNorrisService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreguntasRepo @Inject constructor(
    private val preguntaDao: PreguntaDao,
    private val chuckNorrisService: ChuckNorrisService,
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.dataStore

    private val LAST_QUESTION_KEY = stringPreferencesKey("last_question")

    suspend fun insertarPregunta(pregunta: PreguntaBean) {
        preguntaDao.insertaPregunta(pregunta)
    }

    suspend fun getRandomPregunta(): PreguntaBean? {
        return preguntaDao.getRandomPregunta()
    }

    suspend fun marcarPreguntaLeida(id: Int) {
        preguntaDao.marcarPreguntaLeida(id)
    }

    suspend fun resetPreguntas() {
        preguntaDao.resetPreguntas()
    }

    suspend fun getRandomJoke(): String {
        return try {
            val response = chuckNorrisService.getRandomJoke()
            response.value
        } catch (e: Exception) {
            "Error fetching joke"
        }
    }

    suspend fun saveLastQuestion(question: String) {
        dataStore.edit { preferences ->
            preferences[LAST_QUESTION_KEY] = question
        }
    }

    // Read last question from DataStore
    fun getLastQuestion(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[LAST_QUESTION_KEY]
        }
    }
}
