package com.patronage.patronage.domain

import com.patronage.patronage.data.PreguntaBean
import com.patronage.patronage.data.PreguntasRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRandomPreguntaUseCase  @Inject constructor(
    private val repository: PreguntasRepo
){
    suspend operator fun invoke(): PreguntaBean? {
        return withContext(IO) {
            repository.getRandomPregunta()
        }
    }
}