package com.patronage.patronage.domain.eventos

import com.patronage.patronage.data.EventoBean
import com.patronage.patronage.data.EventosRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRandomEventoUseCase  @Inject constructor(
    private val repository: EventosRepo
){
    suspend operator fun invoke(): EventoBean? {
        return withContext(IO) {
            repository.getRandomEvento()
        }
    }
}