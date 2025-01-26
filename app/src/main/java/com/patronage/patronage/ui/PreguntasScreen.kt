package com.patronage.patronage.ui

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.patronage.patronage.data.PreguntaBean
import com.patronage.patronage.ui.theme.PatronageTheme

@Composable
fun PreguntasScreen(vm: PreguntasViewModel = hiltViewModel()) {
    val pregunta = vm.state.pregunta    //Observer

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CenteredTopBar("PATRONAGE") },
        bottomBar = { CustomBottomBar() },
        content = { paddingValues ->
            if (pregunta != null) {
                PreguntasContent(paddingValues, pregunta) // Pass the question to content
            } else {
                Text(
                    text = "Cargando pregunta...",
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }
    )

    LaunchedEffect(Unit) {
        vm.loadPregunta()
    }
}


@Composable
private fun PreguntasContent(paddingValues: PaddingValues, pregunta: PreguntaBean) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Display the question text
        Text(
            text = pregunta.texto
        )
        // Display answers as buttons
        Button(onClick = { responder(1) }) { Text(pregunta.resp_1) }
        Button(onClick = { responder(2) }) { Text(pregunta.resp_2) }
        Button(onClick = { responder(3) }) { Text(pregunta.resp_3) }
        Button(onClick = { responder(4) }) { Text(pregunta.resp_4) }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreguntasPreview() {
    PatronageTheme() {
        PreguntasScreen()
    }
}

//TODO: Método responder preguntas
fun responder(selectedAnswer: Int) {
    //TODO: Buscar en BDD por id_pregunta si el campo "respuesta_correcta" es la seleccionada
    Log.d("PatronageApplication", "Selected answer: $selectedAnswer")
}