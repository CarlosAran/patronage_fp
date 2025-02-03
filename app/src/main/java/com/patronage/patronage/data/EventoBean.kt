package com.patronage.patronage.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventoBean(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val texto: String,
    val tipo: Int,
    val leido: Int = 0              //0: Aún no salió; 1: Ya salió
)