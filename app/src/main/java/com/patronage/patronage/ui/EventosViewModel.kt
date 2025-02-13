package com.patronage.patronage.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patronage.patronage.data.EventoBean
import com.patronage.patronage.data.EventoDao
import com.patronage.patronage.data.EventosRepo
import com.patronage.patronage.data.PreguntasRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventosViewModel @Inject constructor(
    private val eventoDao: EventoDao,
    private val eventosRepo: EventosRepo
) : ViewModel() {
    // StateFlow que expone el estado actual de las preguntas
    private val _state = MutableStateFlow(EventosState())
    val state: StateFlow<EventosState> get() = _state  // Exponer como StateFlow inmutable

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
                var randomEvento = eventosRepo.getRandomEvento()
                if (randomEvento == null) {
                    eventosRepo.resetEventos()
                    randomEvento = eventosRepo.getRandomEvento()
                }
                if (randomEvento != null) {
                    _state.value = _state.value.copy(evento = randomEvento)
                    eventosRepo.marcarEventoLeido(randomEvento.id)
                } else {
                    _state.value = _state.value.copy(error = "No se han podido cargar los eventos, por favor reinicie la aplicación")
                }
            } catch (e: Throwable) {
                _state.value = _state.value.copy(error = "No se han podido cargar los eventos, por favor reinicie la aplicación")
            }
        }
    }
}