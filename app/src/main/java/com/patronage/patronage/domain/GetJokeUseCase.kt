package com.patronage.patronage.domain

import com.patronage.patronage.data.PreguntasRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetJokeUseCase @Inject constructor(
    private val repository: PreguntasRepo
){
    suspend operator fun invoke(): String {
        return withContext(IO) {
            repository.getRandomJoke()
        }
    }
}