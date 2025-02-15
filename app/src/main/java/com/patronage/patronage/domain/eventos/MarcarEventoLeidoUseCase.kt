package com.patronage.patronage.domain.eventos

import com.patronage.patronage.data.EventosRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MarcarEventoLeidoUseCase @Inject constructor(
    private val repository: EventosRepo
){
    suspend operator fun invoke(id: Int) {
        return withContext(IO) {
            repository.marcarEventoLeido(id)
        }
    }
}