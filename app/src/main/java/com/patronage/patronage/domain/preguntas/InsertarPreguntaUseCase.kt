package com.patronage.patronage.domain.preguntas

import com.patronage.patronage.data.PreguntaBean
import com.patronage.patronage.data.PreguntasRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertarPreguntaUseCase @Inject constructor(
    private val repository: PreguntasRepo
){
    suspend operator fun invoke(pregunta: PreguntaBean) {
        return withContext(IO) {
            repository.insertarPregunta(pregunta)
        }
    }
}