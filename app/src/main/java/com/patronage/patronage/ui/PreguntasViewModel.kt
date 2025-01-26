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
    var state by mutableStateOf(UiState())
        private set
    init {
        loadPregunta()
    }
    data class UiState(
        val pregunta: PreguntaBean? = null
    )

    //Operaciones en la BDD
    fun loadPregunta() {
        viewModelScope.launch {
            val randomPregunta = preguntaDao.getRandomPregunta()
            state = state.copy(pregunta = randomPregunta)
        }
    }
    fun resetPreguntas() {
        viewModelScope.launch {
            preguntaDao.resetPreguntas()
        }
    }
}