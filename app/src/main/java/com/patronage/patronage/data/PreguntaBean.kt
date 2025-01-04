package com.patronage.patronage.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PreguntaBean(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val texto: String,
    val resp_1: String,
    val resp_2: String,
    val resp_3: String,
    val resp_4: String,
    val resp_correcta: Int,
    val recompensa: String
)