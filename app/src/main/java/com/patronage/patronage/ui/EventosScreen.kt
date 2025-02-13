package com.patronage.patronage.ui

import android.content.res.Configuration
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.patronage.patronage.data.EventoBean
import com.patronage.patronage.ui.theme.Dimens
import com.patronage.patronage.ui.theme.PatronageTheme

@Composable
fun EventosScreen(onBackClick: () -> Unit, vm: EventosViewModel = hiltViewModel()) {
    val state by vm.state.collectAsState() // Observer StateFlow

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CenteredTopBar("PATRONAGE") },
        bottomBar = { CustomBottomBar() },
        content = { paddingValues ->
            if (state.evento != null) {
                EventosContent(
                    paddingValues = paddingValues,
                    evento = state.evento!!,
                    error = state.error,
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
                        text = "Cargando evento...",
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        }
    )

    LaunchedEffect(Unit) {
        vm.loadEvento()
    }
}

@Composable
private fun EventosContent(paddingValues: PaddingValues, evento: EventoBean, error: String?, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = Dimens.screenPadding, vertical = Dimens.screenPadding)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = error ?: evento.texto
        )
        Button(onClick = onBackClick) { Text("Volver") }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun EventosPreview() {
    PatronageTheme() {
        EventosScreen(onBackClick = {})
    }
}
