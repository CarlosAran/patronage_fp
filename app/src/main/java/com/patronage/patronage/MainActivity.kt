package com.patronage.patronage

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import com.patronage.patronage.data.sqlite.SQLiteHelper
import com.patronage.patronage.ui.PatronageApp
import com.patronage.patronage.ui.theme.PatronageTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var app: PatronageApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as PatronageApplication
        //Llamo a la base de datos Room
        val db = app.database

        GlobalScope.launch(context = Dispatchers.IO) {
            withContext(Dispatchers.IO){
                Log.d("GlobalScope", "launch: ${Thread.currentThread()}")

                // Crear registros en la base de datos de Room y mostrarlos en el log
                //db.preguntaDao().insertAll(newPreguntaBean("¿Cuánto es uno más uno?", "5", "2", "3", "1", 2, "Ganas 2 monedas"))
                //db.eventoDao().insertAll(newEventoBean("Ha aparecido un brote de tronovirus. Envía a todos tus Guardias al Hospital.", 0))
                val preguntas = db.preguntaDao().getAll();
                preguntas.forEach{
                    Log.d("PatronageDB-Room", "Room ${it.toString()}")
                }


        //SQLite (borrar para release)
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
    //Fin SQLite
        }


        enableEdgeToEdge()
        setContent {
            //Controlo qué ocurre con los permisos
            val permissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) {permissions ->
                permissions.forEach { (permission, isGranted) ->
                    //Log.d("permisos", "$permission: $isGranted")
                }
            }

            PatronageTheme {
                PatronageApp()          //Tareas en segundo plano con comprobaciones y configuraciones

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