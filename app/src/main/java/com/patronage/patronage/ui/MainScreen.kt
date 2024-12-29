package com.patronage.patronage.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.patronage.patronage.R
import com.patronage.patronage.ui.theme.PatronageTheme

@Composable
fun MainScreen(title: String, texto: String, onOpenScreen: (screenName: String) -> Unit) {
    val buttons = listOf(
        Pair("Preguntas", { onOpenScreen("preguntas") }),
        Pair("Eventos", { onOpenScreen("eventos") })
    )
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { CenteredTopBar(title) },
        bottomBar = { CustomBottomBar() },
        content = { paddingValues ->
            MainContent(buttons, paddingValues, title)
        }
    )
}

@Composable
private fun MainContent(buttons: List<Pair<String, () -> Unit>>, paddingValues: PaddingValues, title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 0.dp) // Respeta los paddings del Scaffold
    ) {
        Image(
            painter = painterResource(R.drawable.gorro_policia),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        //Lista de botones
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(62.dp, Alignment.CenterVertically)
        ) {
            items(buttons) { button ->
                CustomButton(
                    texto = button.first,
                    onClick = button.second
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewMainContent() {
    PatronageTheme() {
        MainScreen("PATRONAGE", "", onOpenScreen = { /* No-op for preview */ })
    }
}