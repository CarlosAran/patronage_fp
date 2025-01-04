package com.patronage.patronage.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventoBean(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val texto: String,
    val tipo: Int
)