package com.patronage.patronage.ui

import android.Manifest
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.patronage.patronage.PatronageApplication
import com.patronage.patronage.data.AppDB
import com.patronage.patronage.data.PreguntaBean
import com.patronage.patronage.data.sqlite.SQLiteHelper
import com.patronage.patronage.ui.theme.PatronageTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    private lateinit var app: PatronageApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as PatronageApplication
        //Llamo a la base de datos Room
        val db = app.database

        val preguntaDao = db.preguntaDao()
        val eventoDao = db.eventoDao()

        GlobalScope.launch(context = Dispatchers.IO) {
            withContext(Dispatchers.IO){
                Log.d("GlobalScope", "launch: ${Thread.currentThread()}")

                // Crear registros en la base de datos de Room y mostrarlos en el log
                db.preguntaDao().insertAll(NewPreguntaBean("¿Cuánto es uno más uno?", "5", "2", "3", "1", 2, "Ganas 2 monedas"))
                val preguntas = db.preguntaDao().getAll();
                preguntas.forEach{
                    Log.d("PatronageDB-Room", "Room ${it.toString()}")
                }


                // Operaciones CRUD en SQLite
                val dbHelper = SQLiteHelper(this@MainActivity)
                val dbSQLite = dbHelper.writableDatabase

                // Inserto datos
                val newId = dbHelper.insertEvento(0, "Evento 1")
                Log.d("PatronageDB-SQLite", "Inserted row ID: $newId")
                // Query y mostrar en log
                val eventos = dbHelper.getAllEventos()
                eventos.forEach { evento ->
                    Log.d("PatronageDB-SQLite", "Evento: $evento")
                }

                // Hago un update en el campo "leido"
                val rowsUpdated = dbHelper.updateEvento(newId.toInt(), 1, "Updated Evento")
                Log.d("PatronageDB-SQLite", "Rows updated: $rowsUpdated")
                // Query y mostrar en log
                val eventos2 = dbHelper.getAllEventos()
                eventos2.forEach { evento ->
                    Log.d("PatronageDB-SQLite", "Evento: $evento")
                }

                //Elimino el registro que acabo de añadir
                val rowsDeleted = dbHelper.deleteEvento(newId.toInt())
                Log.d("PatronageDB-SQLite", "Rows deleted: $rowsDeleted")

                dbSQLite.close()
            }
        }


        enableEdgeToEdge()
        setContent {
            //Controlo qué ocurre con los permisos
            val permissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) {permissions ->
                permissions.forEach { (permission, isGranted) ->
                    Log.d("permisos", "$permission: $isGranted")
                }
            }

            PatronageTheme {
                PatronageApp()

                //Pido los permisos al usuario
                LaunchedEffect(Unit) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        permissionLauncher.launch(
                            arrayOf(
                                Manifest.permission.READ_MEDIA_IMAGES,
                                Manifest.permission.READ_MEDIA_VIDEO,
                                Manifest.permission.READ_MEDIA_AUDIO
                            )
                        )
                    } else {
                        permissionLauncher.launch(
                            arrayOf(
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            )
                        )
                    }
                }

            }
        }
    }

    private fun NewPreguntaBean(texto: String, resp_1: String, resp_2: String, resp_3: String, resp_4: String, resp_correcta: Int, recompensa : String): PreguntaBean {
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

    override fun onPause() {
        Log.d(app.TAG, "PatronageApplication onPause")
        super.onPause()
    }

    override fun onResume() {
        Log.d(app.TAG, "PatronageApplication onResume")
        super.onResume()
    }

    override fun onStop() {
        Log.d(app.TAG, "PatronageApplication onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(app.TAG, "PatronageApplication onDestroy")
        super.onDestroy()
    }
}

//@Preview
@Composable
fun PreviewPruebas(){
    ContentPruebas()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewLightMode() {
    PatronageTheme() {
        PatronageApp()
    }
}

@Composable
fun ContentPruebas() {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { CenteredTopBar("PATRONAGE") },
        bottomBar = { CustomBottomBar() },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Respeta los paddings del Scaffold
            ) {
                editableTextField()
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenteredTopBar(titulo: String) {
    CenterAlignedTopAppBar(
        modifier = Modifier.height(96.dp),
        title = {
            Box(Modifier.fillMaxSize()) {
                Text(
                    text = titulo,
                    fontWeight = FontWeight.Bold,
                    fontSize = 46.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    )
}

//Ejemplo de un campo de texto editable
@Composable
fun editableTextField(){
    Column{
        var textfield1 by rememberSaveable { mutableStateOf("") }
        var enabledDisabled by rememberSaveable { mutableStateOf(false) }
        TextField(
            value = textfield1,
            onValueChange = { texto: String ->
            textfield1 = texto
        },
            label = {Text("label")},
            placeholder = { Text("placeholder") },
            enabled = enabledDisabled,
        )
        Box(
            modifier = Modifier
                .background(color = Color.Red)
                .padding(32.dp)
                .fillMaxWidth()
                .clickable { enabledDisabled = !enabledDisabled }
        ){
            Text("Enable textfield")
        }

    }
}


@Composable
fun CustomBottomBar() {
    Box(
        Modifier
            .height(66.dp)
            .fillMaxWidth()
            .background(colorScheme.tertiary)
            .padding(start = 20.dp),) {
        Text(
            text = "Creado por Carlos Arán Tapia",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Left,
            modifier = Modifier.align(Alignment.CenterStart),
        )
    }
}

//------------------------------ BOTONES -----------------------------------
@Composable
fun CustomButton(texto: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.8f)
                .height(200.dp)
        ) {
            Text(texto,
                fontSize = 56.sp)
        }
    }
}


fun AbrirPantalla(navController: NavController, screenName: String) {
    when (screenName) {
        "preguntas" -> navController.navigate("preguntas")
        "eventos" -> navController.navigate("eventos")
        else -> println("Nombre de pantalla no existe")
    }
}