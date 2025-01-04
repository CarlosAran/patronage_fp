package com.patronage.patronage.data
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PreguntaBean::class, EventoBean::class], version = 2)
abstract class AppDB : RoomDatabase() {
    abstract fun preguntaDao(): PreguntaDao
    abstract fun eventoDao(): EventoDao
}