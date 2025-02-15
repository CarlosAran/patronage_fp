package com.patronage.patronage.di

import com.patronage.patronage.data.EventosRepo
import com.patronage.patronage.data.PreguntasRepo
import com.patronage.patronage.domain.GetJokeUseCase
import com.patronage.patronage.domain.eventos.GetRandomEventoUseCase
import com.patronage.patronage.domain.eventos.InsertarEventoUseCase
import com.patronage.patronage.domain.eventos.MarcarEventoLeidoUseCase
import com.patronage.patronage.domain.eventos.ResetEventosUseCase
import com.patronage.patronage.domain.preguntas.GetRandomPreguntaUseCase
import com.patronage.patronage.domain.preguntas.InsertarPreguntaUseCase
import com.patronage.patronage.domain.preguntas.MarcarPreguntaLeidaUseCase
import com.patronage.patronage.domain.preguntas.ResetPreguntasUseCase
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

    //PREGUNTAS
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

    //EVENTOS
    @Provides
    @Singleton
    fun provideGetRandomEventoUseCase(eventosRepo: EventosRepo): GetRandomEventoUseCase {
        return GetRandomEventoUseCase(eventosRepo)
    }

    @Provides
    @Singleton
    fun provideInsertarEventoUseCase(eventosRepo: EventosRepo): InsertarEventoUseCase {
        return InsertarEventoUseCase(eventosRepo)
    }

    @Provides
    @Singleton
    fun provideMarcarEventoLeidoUseCase(eventosRepo: EventosRepo): MarcarEventoLeidoUseCase {
        return MarcarEventoLeidoUseCase(eventosRepo)
    }

    @Provides
    @Singleton
    fun provideResetEventosUseCase(eventosRepo: EventosRepo): ResetEventosUseCase {
        return ResetEventosUseCase(eventosRepo)
    }
}
