package com.patronage.patronage.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patronage.patronage.data.PreguntaBean
import com.patronage.patronage.data.PreguntaDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreguntasViewModel @Inject constructor(
    private val preguntaDao: PreguntaDao
) : ViewModel() {
    var state by mutableStateOf(PreguntasState())
        private set
    init {
        loadPregunta()
    }
    data class PreguntasState(
        val pregunta: PreguntaBean? = null,
        val error: String? = null
    )

    //Operaciones en la BDD
    fun loadPregunta() {
        viewModelScope.launch {
            try {
                var randomPregunta = preguntaDao.getRandomPregunta()
                if (randomPregunta == null) {
                    preguntaDao.resetPreguntas()
                    randomPregunta = preguntaDao.getRandomPregunta()
                }
                if (randomPregunta != null) {
                    state = state.copy(pregunta = randomPregunta)
                    preguntaDao.marcarPreguntaLeida(randomPregunta.id)
                } else {
                    state = state.copy(error = "No se han podido cargar las preguntas, por favor reinicie la aplicación")
                }
            } catch (e: Throwable) {
                state = state.copy(error = "No se han podido cargar las preguntas, por favor reinicie la aplicación")
            }
        }
    }
}