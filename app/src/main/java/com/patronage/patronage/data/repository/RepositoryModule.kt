package com.patronage.patronage.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.patronage.patronage.data.EventoDao
import com.patronage.patronage.data.EventosRepo
import com.patronage.patronage.data.PrefsRepo
import com.patronage.patronage.data.PreguntaDao
import com.patronage.patronage.data.PreguntasRepo
import com.patronage.patronage.data.dataStore
import com.patronage.patronage.data.network.ChuckNorrisService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePreguntasRepository(
        preguntaDao: PreguntaDao,
        chuckNorrisService: ChuckNorrisService,
        context: Context
    ): PreguntasRepo {
        return PreguntasRepo(preguntaDao, chuckNorrisService, context)
    }

    @Provides
    @Singleton
    fun provideEventosRepository(
        eventoDao: EventoDao,
        context: Context
    ): EventosRepo {
        return EventosRepo(eventoDao, context)
    }
    @Provides
    @Singleton
    fun provideDataStore(@dagger.hilt.android.qualifiers.ApplicationContext context: Context): DataStore<androidx.datastore.preferences.core.Preferences> {
        return context.dataStore
    }
    @Provides
    @Singleton
    fun providePreferencesRepo(dataStore: DataStore<Preferences>, @dagger.hilt.android.qualifiers.ApplicationContext context: Context): PrefsRepo {
        return PrefsRepo(context, dataStore)
    }
}
