package com.patronage.patronage.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDB {
        return Room.databaseBuilder(
            context,
            AppDB::class.java,
            "PatronageDB-Room"
        ).addMigrations(AppDB.MIGRATION_2_3)
            .build()
    }

    @Provides
    fun providePreguntaDao(database: AppDB): PreguntaDao {
        return database.preguntaDao()
    }

    @Provides
    fun provideEventoDao(database: AppDB): EventoDao {
        return database.eventoDao()
    }

    private fun newPreguntaBean(texto: String, resp_1: String, resp_2: String, resp_3: String, resp_4: String, resp_correcta: Int, recompensa : String): PreguntaBean {
        return PreguntaBean(
            texto = texto,
            resp_1 = resp_1,
            resp_2 = resp_2,
            resp_3 = resp_3,
            resp_4 = resp_4,
            resp_correcta = resp_correcta,
            recompensa = recompensa
        )
    }

    private fun newEventoBean(texto: String, tipo: Int): EventoBean {
        return EventoBean(
            texto = texto,
            tipo = tipo
        )
    }
}
