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
}
