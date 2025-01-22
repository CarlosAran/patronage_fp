package com.patronage.patronage.data
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventoDao {
    @Query("SELECT * FROM EventoBean")
    suspend fun getAllEventos(): List<EventoBean>

    @Insert
    suspend fun insertaEvento(evento: EventoBean)
    @Insert
    suspend fun insertAll(vararg eventos: EventoBean)

    @Delete
    suspend fun delete(evento: EventoBean)
    @Query("DELETE FROM EventoBean")
    suspend fun truncateTable()
}