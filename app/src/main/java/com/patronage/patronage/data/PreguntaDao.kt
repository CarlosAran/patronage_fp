package com.patronage.patronage.data
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PreguntaDao {
    @Query("SELECT * FROM PreguntaBean")
    suspend fun getAll(): List<PreguntaBean>

    @Insert
    suspend fun insertaPregunta(pregunta: PreguntaBean)
    @Insert
    suspend fun insertAll(vararg pregunta: PreguntaBean)

    @Delete
    suspend fun delete(pregunta: PreguntaBean)
    @Query("DELETE FROM PreguntaBean")
    suspend fun truncateTable()
}