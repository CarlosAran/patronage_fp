package com.patronage.patronage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.patronage.patronage.ui.theme.PatronageTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PatronageTheme {
                Content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PatronageTheme {
        Content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content() {
    val buttons = listOf(
        Pair("Preguntas", { AbrirPantalla("preguntas") }),
        Pair("Eventos", { AbrirPantalla("eventos") })
    )

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { CenteredTopBar("PATRONAGE") },
        bottomBar = { CustomBottomBar() },
        /*floatingActionButton = @androidx.compose.runtime.Composable {
            FloatingActionButton(onClick = { /* Acción del FAB */ }) {
                Icon(Icons.Default.Add, contentDescription = "Añadir")
            }
        },*/
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 0.dp) // Respeta los paddings del Scaffold
            ) {
                // Imagen de fondo
                Image(
                    painter = painterResource(R.drawable.gorro_policia),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                // Contenido principal sobre la imagen
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
    )
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

@Composable
fun CustomBottomBar() {
    Box(Modifier.height(66.dp).fillMaxWidth()
        .background(Color.Blue)
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
            modifier = Modifier.align(Alignment.Center)
                .fillMaxWidth(0.8f)
                .height(200.dp)
        ) {
            Text(texto,
                fontSize = 56.sp)
        }
    }
}


fun AbrirPantalla(pantalla: String) {
    /*TODO: Abrir la pantalla que se pasa como argumento*/
}