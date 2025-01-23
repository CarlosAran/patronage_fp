package com.patronage.patronage.data
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PreguntaDao {
    @Query("SELECT * FROM PreguntaBean")
    suspend fun getAll(): List<PreguntaBean>
    @Query("SELECT * FROM PreguntaBean WHERE leida = 0 ORDER BY RANDOM() LIMIT 1;")
    suspend fun getRandomPregunta(): PreguntaBean

    @Insert
    suspend fun insertaPregunta(pregunta: PreguntaBean)
    @Insert
    suspend fun insertAll(vararg pregunta: PreguntaBean)

    //Se llamará cuando el usuario pulse para comenzar una nueva partida o cuando ya se hayan leido todas
    @Query("UPDATE PreguntaBean SET leida = 0")
    suspend fun resetPreguntas()
    @Query("UPDATE PreguntaBean SET leida = 1 WHERE id = :id")
    suspend fun marcarPreguntaLeida(id: Int)

    @Delete
    suspend fun delete(pregunta: PreguntaBean)
    @Query("DELETE FROM PreguntaBean")
    suspend fun truncateTable()
}