package com.patronage.patronage.ui.preguntas

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.patronage.patronage.data.PreguntaBean
import com.patronage.patronage.ui.main.CenteredTopBar
import com.patronage.patronage.ui.main.CustomBottomBar
import com.patronage.patronage.ui.theme.Dimens
import com.patronage.patronage.ui.theme.PatronageTheme

@Composable
fun PreguntasScreen(onBackClick: () -> Unit, vm: PreguntasViewModel = hiltViewModel()) {
    val state by vm.state.collectAsState() // Observer StateFlow

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CenteredTopBar("PATRONAGE") },
        bottomBar = { CustomBottomBar() },
        content = { paddingValues ->
            if (state.pregunta != null) {
                PreguntasContent(
                    paddingValues = paddingValues,
                    pregunta = state.pregunta!!,
                    error = state.error,
                    joke = state.joke,
                    onAnswerClick = { answer -> vm.responder(answer) },
                    onBackClick = onBackClick
                )
            } else {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(horizontal = Dimens.screenPadding, vertical = Dimens.screenPadding)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Cargando pregunta...",
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        }
    )

    if (state.showDialog) {
        AlertDialog(
            onDismissRequest = { vm.dismissDialog() },
            title = { Text(text = state.dialogTitle) },
            text = { Text(text = state.dialogMessage) },
            confirmButton = {
                Button(onClick = {
                    vm.dismissDialog()
                    vm.loadPregunta()
                }) {
                    Text("Nueva pregunta")
                }
            },
            dismissButton = {
                Button(onClick = {
                    vm.dismissDialog()
                    onBackClick()
                }) {
                    Text("Volver")
                }
            }
        )
    }

    /*LaunchedEffect(Unit) {
        vm.loadPregunta()
    }*/
}


@Composable
private fun PreguntasContent(paddingValues: PaddingValues, pregunta: PreguntaBean, error: String?, onAnswerClick: (Int) -> Unit, joke: String?, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = Dimens.screenPadding, vertical = Dimens.screenPadding)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = error ?: pregunta.texto
        )
        Button(onClick = { onAnswerClick(1) }) { Text(pregunta.resp_1) }
        Button(onClick = { onAnswerClick(2) }) { Text(pregunta.resp_2) }
        Button(onClick = { onAnswerClick(3) }) { Text(pregunta.resp_3) }
        Button(onClick = { onAnswerClick(4) }) { Text(pregunta.resp_4) }

        //Sólo para testear que funciona lo de los repositorios
        joke?.let {
            Text(
                text = "🤠 Chuck Norris says: $it",
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        //Button(onClick = onBackClick) { Text("Volver") }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreguntasPreview() {
    PatronageTheme() {
        PreguntasScreen(onBackClick = {})
    }
}