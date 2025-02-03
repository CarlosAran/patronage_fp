package com.patronage.patronage.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patronage.patronage.data.PreguntaBean
import com.patronage.patronage.data.PreguntaDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreguntasViewModel @Inject constructor(
    private val preguntaDao: PreguntaDao
) : ViewModel() {
    // StateFlow que expone el estado actual de las preguntas
    private val _state = MutableStateFlow(PreguntasState())
    val state: StateFlow<PreguntasState> get() = _state  // Exponer como StateFlow inmutable

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
                    _state.value = _state.value.copy(pregunta = randomPregunta)
                    preguntaDao.marcarPreguntaLeida(randomPregunta.id)
                } else {
                    _state.value = _state.value.copy(error = "No se han podido cargar las preguntas, por favor reinicie la aplicación")
                }
            } catch (e: Throwable) {
                _state.value = _state.value.copy(error = "No se han podido cargar las preguntas, por favor reinicie la aplicación")
            }
        }
    }
}