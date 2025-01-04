package com.patronage.patronage.data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventoDao {
    @Insert
    suspend fun insertaEvento(evento: EventoBean)

    @Query("SELECT * FROM EventoBean")
    suspend fun getAllEventos(): List<EventoBean>
}