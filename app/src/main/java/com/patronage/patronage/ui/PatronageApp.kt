package com.patronage.patronage.ui

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.patronage.patronage.data.network.ChuckNorrisService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

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
    var jokeString by androidx.compose.runtime.remember { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "Main") {
        //Estados para la navegación
        composable("Main"){
            //MainScreen(){ navController.navigate("Preguntas") }
            MainScreen(state.title, "$jokeString") { screenName -> AbrirPantalla(navController, screenName) }
        }
        composable("Preguntas") { PreguntasScreen() }
        composable("Eventos") { EventosScreen() }
    }


    val json = Json{
        ignoreUnknownKeys = true
    }
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.chucknorris.io/")
        .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
        .build()

    val service = retrofit.create(ChuckNorrisService::class.java)
    LaunchedEffect(Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            delay(200)
            val joke = service.getRandomJoke()
            Log.d("PatronageApplication", "$joke")
            jokeString = joke.value
        }
    }


    //NavHost() {}
}
