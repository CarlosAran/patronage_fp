package com.patronage.patronage.ui.preguntas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patronage.patronage.data.PreguntaBean
import com.patronage.patronage.data.PreguntaDao
import com.patronage.patronage.data.PreguntasRepo
import com.patronage.patronage.domain.GetJokeUseCase
import com.patronage.patronage.domain.GetRandomPreguntaUseCase
import com.patronage.patronage.domain.MarcarPreguntaLeidaUseCase
import com.patronage.patronage.domain.ResetPreguntasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreguntasViewModel @Inject constructor(
    private val preguntaDao: PreguntaDao,
    private val preguntasRepo: PreguntasRepo,
    private val getRandomPreguntaUseCase: GetRandomPreguntaUseCase,
    private val resetPreguntasUseCase: ResetPreguntasUseCase,
    private val marcarPreguntaLeidaUseCase: MarcarPreguntaLeidaUseCase,
    private val getJokeUseCase: GetJokeUseCase
) : ViewModel() {
    // StateFlow que expone el estado actual de las preguntas
    private val _state = MutableStateFlow(PreguntasState())
    val state: StateFlow<PreguntasState> get() = _state  // Exponer como StateFlow inmutable

    init {
        loadPregunta()
        loadJoke()                      //Temporalmente hasta que cree la API con preguntas y eventos
    }
    data class PreguntasState(
        val pregunta: PreguntaBean? = null,
        val error: String? = null,
        val joke: String? = null
    )

    //Operaciones en la BDD
    fun loadPregunta() {
        viewModelScope.launch {
            /* Repo
            try {
                var randomPregunta = preguntasRepo.getRandomPregunta()
                if (randomPregunta == null) {
                    preguntasRepo.resetPreguntas()
                    randomPregunta = preguntasRepo.getRandomPregunta()
                }
                if (randomPregunta != null) {
                    _state.value = _state.value.copy(pregunta = randomPregunta)
                    preguntasRepo.marcarPreguntaLeida(randomPregunta.id)
                } else {
                    _state.value = _state.value.copy(error = "No se han podido cargar las preguntas, por favor reinicie la aplicación")
                }
            } catch (e: Throwable) {
                _state.value = _state.value.copy(error = "No se han podido cargar las preguntas, por favor reinicie la aplicación")
            }
            */
            /*UseCase*/
            try {
                var randomPregunta = getRandomPreguntaUseCase()
                if (randomPregunta == null) {
                    resetPreguntasUseCase()
                    randomPregunta = getRandomPreguntaUseCase()
                }
                if (randomPregunta != null) {
                    _state.value = _state.value.copy(pregunta = randomPregunta)
                    marcarPreguntaLeidaUseCase(randomPregunta.id)
                } else {
                    _state.value = _state.value.copy(error = "No se han podido cargar las preguntas, por favor reinicie la aplicación")
                }
            } catch (e: Throwable) {
                _state.value = _state.value.copy(error = "No se han podido cargar las preguntas, por favor reinicie la aplicación")
            }
        }
    }

    fun loadJoke() {
        viewModelScope.launch {
            try {
                /*val joke = preguntasRepo.getRandomJoke()
                _state.value = _state.value.copy(joke = joke)*/
                /*UseCase*/
                val joke = getJokeUseCase()
                _state.value = _state.value.copy(joke = joke)
            } catch (e: Exception) {
                _state.value = _state.value.copy(joke = "Error fetching joke")
            }
        }
    }
}