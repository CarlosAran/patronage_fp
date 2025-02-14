package com.patronage.patronage.domain

import com.patronage.patronage.data.PrefsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadPreguntasLeidasUseCase @Inject constructor(
    private val prefsRepo: PrefsRepo
) {
    operator fun invoke(): Flow<Int> {
        return prefsRepo.readPreguntasLeidas
    }
}