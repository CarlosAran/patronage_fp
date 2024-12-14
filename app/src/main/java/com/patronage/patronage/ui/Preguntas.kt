package com.patronage.patronage.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun PreguntasScreen() {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { CenteredTopBar("PATRONAGE") },
        bottomBar = { CustomBottomBar() },
        content = { paddingValues ->
            PreguntasContent(paddingValues)
        }
    )

    //id_pregunta...
}

@Composable
private fun PreguntasContent(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Pregunta1"
        )
        //TODO: LazyColumn/LazyList con las posibles respuestas como botones
        Button( onClick = { responder() },) { Text("Respuesta 1") }
        Button( onClick = { responder() },) { Text("Respuesta 2") }
        Button( onClick = { responder() },) { Text("Respuesta 3") }
        Button( onClick = { responder() },) { Text("Respuesta 4") }
    }
}

@Preview
@Composable
fun PreguntasPreview() {
    PreguntasScreen()
}

//TODO: Método responder preguntas
fun responder() {
    //TODO: Buscar en BDD por id_pregunta si el campo "respuesta_correcta" es la seleccionada

}