package edu.ucne.appliedbarbershop

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.ui.clientees.PerfilViewModel
import edu.ucne.appliedbarbershop.ui.theme.DancingScript
import edu.ucne.appliedbarbershop.utils.Constantes
import edu.ucne.appliedbarbershop.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroPerfilScreen(
    navController: NavController,
    id: Int = 0,
    viewModel: PerfilViewModel = hiltViewModel()
) {
    val localContext = LocalContext.current
    Scaffold(
        bottomBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(bottom = 100.dp)
                    .fillMaxWidth()
            ) {
                Button(
                    enabled = viewModel.enableSubmit,
                    onClick = {
                        if (viewModel.save().value == true)
                            navController.navigate(Screen.ConfirmaRegistroPerfilScreen.Route)
                        else
                            Toast.makeText(localContext, "No se pudo guardar!", Toast.LENGTH_SHORT).show()
                    }) {
                    Text(text = "Siguiente")
                }
            }
        }
    ) {
        it
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(39.dp))
            Text(
                text = "Permítenos\nConocerte",
                textAlign = TextAlign.Center,
                lineHeight = 56.sp,
                fontSize = 56.sp,
                modifier = Modifier.width(312.dp)
            )
            Spacer(modifier = Modifier.height(38.dp))
            OutlinedTextField(
                label = { Text(text = "Nombre") },
                value = viewModel.nombre,
                onValueChange = { viewModel.onNombreChange(it) })
            OutlinedTextField(
                label = { Text(text = "Apellido") },
                value = viewModel.apellido,
                onValueChange = { viewModel.onApellidoChange(it) })
            OutlinedTextField(
                label = { Text(text = "Celular") },
                value = viewModel.celular,
                onValueChange = { viewModel.onCelularChange(it) })
            OutlinedTextField(
                label = { Text(text = "Fecha de Nacimiento") },
                value = viewModel.fechaNacimiento,
                onValueChange = { viewModel.onFechaNacimientoChange(it) })
        }
    }
}