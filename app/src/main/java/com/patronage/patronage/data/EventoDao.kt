package com.patronage.patronage.data
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventoDao {
    @Query("SELECT * FROM EventoBean")
    suspend fun getAllEventos(): List<EventoBean>
    @Query("SELECT * FROM EventoBean WHERE leido = 0 ORDER BY RANDOM() LIMIT 1;")
    suspend fun getRandomEvento(): EventoBean?

    @Insert
    suspend fun insertaEvento(evento: EventoBean)
    @Insert
    suspend fun insertAll(vararg eventos: EventoBean)

    //Se llamará cuando el usuario pulse para comenzar una nueva partida o cuando ya hayan salido todos
    @Query("UPDATE EventoBean SET leido = 0")
    suspend fun resetEventos()
    @Query("UPDATE EventoBean SET leido = 1 WHERE id = :id")
    suspend fun marcarEventoLeido(id: Int)

    @Delete
    suspend fun delete(evento: EventoBean)
    @Query("DELETE FROM EventoBean")
    suspend fun truncateTable()
}