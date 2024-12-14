package com.patronage.patronage.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patronage.patronage.R
import com.patronage.patronage.ui.theme.PatronageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PatronageTheme {
                PatronageApp()
            }
        }
    }
}

//@Preview
@Composable
fun PreviewPruebas(){
    ContentPruebas()
}

@Preview(showBackground = true, name = "Light Mode")
@Composable
fun PreviewLightMode() {
    PatronageTheme(darkTheme = false) {
        PatronageApp()
    }
}

//@Preview(showBackground = true, name = "Dark Mode")
@Composable
fun PreviewDarkMode() {
    PatronageTheme(darkTheme = true) {
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
                    .padding(top = 100.dp) // Respeta los paddings del Scaffold
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