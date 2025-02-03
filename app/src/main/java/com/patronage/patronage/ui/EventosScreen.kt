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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.patronage.patronage.data.EventoBean
import com.patronage.patronage.ui.theme.Dimens
import com.patronage.patronage.ui.theme.PatronageTheme

@Composable
fun EventosScreen(navController: NavController, vm: EventosViewModel = hiltViewModel()) {
    val evento = vm.state.evento    //Observer
    val error = vm.state.error    //Observer

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CenteredTopBar("PATRONAGE") },
        bottomBar = { CustomBottomBar() },
        content = { paddingValues ->
            if (evento != null) {
                EventosContent(navController, paddingValues, evento, error)
            } else {
                Text(
                    text = "Cargando evento...",
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                BackButton(navController)
            }
        }
    )

    LaunchedEffect(Unit) {
        vm.loadEvento()
    }
}

@Composable
private fun EventosContent(navController: NavController, paddingValues: PaddingValues, evento: EventoBean, error: String?) {
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
        BackButton(navController)
    }
}

@Composable
fun BackButton(navController: NavController) {
    Button(modifier =
        Modifier.padding(vertical = 18.dp), onClick = { navController.popBackStack() }) {
        Text("Back")
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun EventosPreview() {
    val navController = rememberNavController()

    PatronageTheme() {
        EventosScreen(navController)
    }
}
