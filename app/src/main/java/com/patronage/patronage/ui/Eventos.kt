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
fun EventosScreen() {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { CenteredTopBar("PATRONAGE") },
        bottomBar = { CustomBottomBar() },
        content = { paddingValues ->
            EventosContent(paddingValues)
        }
    )

    //id_evento...
}

@Composable
private fun EventosContent(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Evento1"
        )
    }
}

@Preview
@Composable
fun EventosPreview() {
    EventosScreen()
}
