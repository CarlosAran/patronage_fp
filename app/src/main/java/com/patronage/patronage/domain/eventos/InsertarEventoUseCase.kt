package com.patronage.patronage.domain.eventos

import com.patronage.patronage.data.EventoBean
import com.patronage.patronage.data.EventosRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertarEventoUseCase @Inject constructor(
    private val repository: EventosRepo
){
    suspend operator fun invoke(evento: EventoBean) {
        return withContext(IO) {
            repository.insertarEvento(evento)
        }
    }
}