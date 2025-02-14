package com.patronage.patronage.di

import com.patronage.patronage.data.PreguntasRepo
import com.patronage.patronage.domain.GetJokeUseCase
import com.patronage.patronage.domain.GetRandomPreguntaUseCase
import com.patronage.patronage.domain.InsertarPreguntaUseCase
import com.patronage.patronage.domain.MarcarPreguntaLeidaUseCase
import com.patronage.patronage.domain.ResetPreguntasUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetJokeUseCase(preguntasRepo: PreguntasRepo): GetJokeUseCase {
        return GetJokeUseCase(preguntasRepo)
    }

    @Provides
    @Singleton
    fun provideGetRandomPreguntaUseCase(preguntasRepo: PreguntasRepo): GetRandomPreguntaUseCase {
        return GetRandomPreguntaUseCase(preguntasRepo)
    }

    @Provides
    @Singleton
    fun provideInsertarPreguntaUseCase(preguntasRepo: PreguntasRepo): InsertarPreguntaUseCase {
        return InsertarPreguntaUseCase(preguntasRepo)
    }

    @Provides
    @Singleton
    fun provideMarcarPreguntaLeidaUseCase(preguntasRepo: PreguntasRepo): MarcarPreguntaLeidaUseCase {
        return MarcarPreguntaLeidaUseCase(preguntasRepo)
    }

    @Provides
    @Singleton
    fun provideResetPreguntasUseCase(preguntasRepo: PreguntasRepo): ResetPreguntasUseCase {
        return ResetPreguntasUseCase(preguntasRepo)
    }
}
