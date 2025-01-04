package com.patronage.patronage.data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PreguntaDao {
    @Insert
    suspend fun insertaPregunta(pregunta: PreguntaBean)

    @Query("SELECT * FROM PreguntaBean")
    suspend fun getAllPreguntas(): List<PreguntaBean>
}