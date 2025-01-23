package com.patronage.patronage.data
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [PreguntaBean::class, EventoBean::class], version = 3)
abstract class AppDB : RoomDatabase() {
    abstract fun preguntaDao(): PreguntaDao
    abstract fun eventoDao(): EventoDao

    companion object {
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Add the new columns to the existing tables
                database.execSQL("ALTER TABLE PreguntaBean ADD COLUMN leida INTEGER NOT NULL DEFAULT 0")
                database.execSQL("ALTER TABLE EventoBean ADD COLUMN leido INTEGER NOT NULL DEFAULT 0")
            }
        }
    }
}