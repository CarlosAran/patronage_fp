package com.patronage.patronage.data
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PreguntaBean::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun preguntaDao(): PreguntaDao
    abstract fun eventoDao(): EventoDao
}