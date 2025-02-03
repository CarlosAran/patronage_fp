package com.patronage.patronage.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patronage.patronage.data.EventoBean
import com.patronage.patronage.data.EventoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventosViewModel @Inject constructor(
    private val eventoDao: EventoDao
) : ViewModel() {
    var state by mutableStateOf(EventosState())
        private set
    init {
        loadEvento()
    }
    data class EventosState(
        val evento: EventoBean? = null,
        val error: String? = null
    )

    //Operaciones en la BDD
    fun loadEvento() {
        viewModelScope.launch {
            try {
                var randomEvento = eventoDao.getRandomEvento()
                if (randomEvento == null) {
                    eventoDao.resetEventos()
                    randomEvento = eventoDao.getRandomEvento()
                }
                if (randomEvento != null) {
                    state = state.copy(evento = randomEvento)
                    eventoDao.marcarEventoLeido(randomEvento.id)
                } else {
                    state = state.copy(error = "No se han podido cargar los eventos, por favor reinicie la aplicación")
                }
            } catch (e: Throwable) {
                state = state.copy(error = "No se han podido cargar los eventos, por favor reinicie la aplicación")
            }
        }
    }
}