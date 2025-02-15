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
    val recompensa: String,
    val leida: Int = 0 // 0: No leída; 1: Leída en esta sesión
) {
    companion object {
        fun new(texto: String, resp_1: String, resp_2: String, resp_3: String, resp_4: String, resp_correcta: Int, recompensa: String): PreguntaBean {
            return PreguntaBean(
                texto = texto,
                resp_1 = resp_1,
                resp_2 = resp_2,
                resp_3 = resp_3,
                resp_4 = resp_4,
                resp_correcta = resp_correcta,
                recompensa = recompensa
            )
        }
    }
}
