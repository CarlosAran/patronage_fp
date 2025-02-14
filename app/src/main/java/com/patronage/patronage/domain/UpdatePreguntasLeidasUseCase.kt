package com.patronage.patronage.domain

import com.patronage.patronage.data.PrefsRepo
import javax.inject.Inject

class UpdatePreguntasLeidasUseCase @Inject constructor(
    private val preferencesRepo: PrefsRepo
) {
    suspend operator fun invoke(value: Int) {
        preferencesRepo.writePreguntasLeidas(value)
    }
}