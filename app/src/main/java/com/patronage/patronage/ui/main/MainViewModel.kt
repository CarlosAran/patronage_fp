package com.patronage.patronage.ui.main

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.patronage.patronage.domain.GetRandomPreguntaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRandomPreguntaUseCase: GetRandomPreguntaUseCase
) : ViewModel() {
    fun AbrirPantalla(navController: NavController, screenName: String) {
        when (screenName) {
            "preguntas" -> navController.navigate("preguntas")
            "eventos" -> navController.navigate("eventos")
            else -> println("Nombre de pantalla no existe")
        }
    }
}