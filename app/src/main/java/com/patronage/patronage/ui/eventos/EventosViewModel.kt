package com.patronage.patronage.ui.eventos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patronage.patronage.data.EventoBean
import com.patronage.patronage.data.EventoDao
import com.patronage.patronage.data.EventosRepo
import com.patronage.patronage.domain.eventos.GetRandomEventoUseCase
import com.patronage.patronage.domain.eventos.MarcarEventoLeidoUseCase
import com.patronage.patronage.domain.eventos.ResetEventosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventosViewModel @Inject constructor(
    private val eventoDao: EventoDao,
    private val eventosRepo: EventosRepo,
    private val getRandomEventoUseCase: GetRandomEventoUseCase,
    private val resetEventosUseCase: ResetEventosUseCase,
    private val marcarEventoLeidoUseCase: MarcarEventoLeidoUseCase
) : ViewModel() {
    // StateFlow que expone el estado actual de los eventos
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
            /*Repo*/
            /*try {
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
            }*/
            /*UseCase*/
            try {
                var randomEvento = getRandomEventoUseCase()
                if (randomEvento == null) {
                    resetEventosUseCase()
                    randomEvento = getRandomEventoUseCase()
                }
                if (randomEvento != null) {
                    _state.value = _state.value.copy(evento = randomEvento)
                    marcarEventoLeidoUseCase(randomEvento.id)
                } else {
                    _state.value =
                        _state.value.copy(error = "No se han podido cargar los eventos, por favor reinicie la aplicación")
                }
            } catch (e: Throwable) {
                _state.value = _state.value.copy(error = "No se han podido cargar los eventos, por favor reinicie la aplicación")
            }
        }
    }
}