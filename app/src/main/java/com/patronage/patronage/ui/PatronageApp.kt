package com.patronage.patronage.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.patronage.patronage.data.network.NetworkModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    //Navegación
    NavHost(navController = navController, startDestination = "Main") {
        //Estados para la navegación
        composable("Main"){
            //MainScreen(){ navController.navigate("Preguntas") }
            MainScreen(state.title, "$jokeString") { screenName -> AbrirPantalla(navController, screenName) }
        }
        composable("Preguntas") { PreguntasScreen() }
        composable("Eventos") { EventosScreen() }
    }

    //Llamada a API
    if (checkForInternet(LocalContext.current)) {
        //Llamo a la API para descargar las preguntas
        //TODO: Cambiar API chucknorris por API de preguntas
        val json = NetworkModule.providesJson()
        val retrofit = NetworkModule.providesChuckNorrisAPI(json)
        val service = NetworkModule.providesChuckNorrisService(retrofit)

        LaunchedEffect(Unit) {
            GlobalScope.launch(Dispatchers.Main) {
                delay(200)
                val joke = service.getRandomJoke()
                Log.d("PatronageApplication", "$joke")
                jokeString = joke.value
            }
        }
        //TODO: Si hay versión nueva, descargar y actualizar la BDD
        //if(...) {
            //Toast.makeText(LocalContext.current, "Se están descargando nuevas preguntas", Toast.LENGTH_LONG).show()
            //TODO: ¿Necesito permisos para modificar la BDD? Controlar caso sin permisos. Si el usuario no los da que ni intente conectarse a internet.
        //}
    } else {
        //Si no hay conexión a internet, se salta la llamada a la API y aviso al usuario
        Toast.makeText(LocalContext.current, "Se necesita conexión a internet para actualizar las preguntas", Toast.LENGTH_LONG).show()
    }
}

private fun checkForInternet(context: Context): Boolean {
    // register activity with the connectivity manager service
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // A partir de la versión "M", hay que usar NetworkCapabilities
    // para comprobar la conexión a internet
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            // Conectado a Wi-Fi
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            // Conectado a datos móvil
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            // else return false
            else -> false
        }
    } else {
        @Suppress("DEPRECATION") val networkInfo =
            connectivityManager.activeNetworkInfo ?: return false
        @Suppress("DEPRECATION")
        return networkInfo.isConnected
    }
}
