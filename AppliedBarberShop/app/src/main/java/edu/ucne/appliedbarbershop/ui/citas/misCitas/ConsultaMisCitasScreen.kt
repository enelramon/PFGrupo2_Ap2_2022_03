package edu.ucne.appliedbarbershop

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.ui.citas.CitaViewModel
import edu.ucne.appliedbarbershop.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaMisCitasScreen(
    navController: NavController,
    viewModel: CitaViewModel = hiltViewModel()
) {
    val localContext = LocalContext.current
    Scaffold(
        floatingActionButton = {
            Button(onClick = { navController.navigate(Screen.RegistroCita_BarberoScreen.Route) }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Localized description"
                )
            }
        }
    ) {
        it
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
        ) {
            CardComponent(
                titulo = "Hola que hacen",
                subtitulo = "Que hacen",
                cuerpo = "Heyy",
                btn1Nombre = "Editar",
                btn2Nombre = "Eliminar",
                btn1OnClick = {},
                btn2OnClick = {}
            )
            Spacer(modifier = Modifier.height(12.dp))
            CardComponent(
                titulo = "Hola que hacen",
                subtitulo = "Que hacen",
                cuerpo = "Heyy",
                btn1Nombre = "Editar",
                btn2Nombre = "Eliminar",
                btn1OnClick = {},
                btn2OnClick = {}
            )
        }
    }
}

@Composable
fun CardComponent(
    titulo: String,
    subtitulo: String?,
    cuerpo: String,
    btn1Nombre: String,
    btn2Nombre: String,
    btn1OnClick: () -> Unit,
    btn2OnClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color(0, 0, 0, 12)))
    ) {
        CardHeaderComponent(titulo, subtitulo)
        CardBodyComponent(text = cuerpo)
        CardFooterComponent(
            btn1Nombre = btn1Nombre,
            btn2Nombre = btn2Nombre,
            btn1OnClick = btn1OnClick,
            btn2OnClick = btn2OnClick
        )
    }
}

@Composable
fun CardHeaderComponent(titulo: String, subtitulo: String?) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = titulo,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = subtitulo ?: "",
            fontSize = 14.sp
        )
    }
}

@Composable
fun CardBodyComponent(text: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = text)
    }
}

@Composable
fun CardFooterComponent(
    btn1Nombre: String,
    btn1OnClick: () -> Unit?,
    btn2Nombre: String,
    btn2OnClick: () -> Unit?
) {
    Row(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { btn1OnClick() }) {
            Text(text = btn1Nombre)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Button(onClick = { btn2OnClick() }) {
            Text(text = btn2Nombre)
        }
    }
}