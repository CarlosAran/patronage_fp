package com.patronage.patronage.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/*@Serializable
object Preguntas
@Serializable
object Main*/

data class MainState (
    val title: String = "PATRONAGE",
    val texto: String = "texto",
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatronageApp() {
    val state = MainState()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Main") {
        //Estados para la navegación
        composable("Main"){
            //MainScreen(){ navController.navigate("Preguntas") }
            MainScreen(state.title, state.texto) { screenName -> AbrirPantalla(navController, screenName) }
        }
        composable("Preguntas") { PreguntasScreen() }
        composable("Eventos") { EventosScreen() }
    }


    //NavHost() {}
}
