package edu.ucne.appliedbarbershop

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.ui.clientes.ClienteViewModel
import edu.ucne.appliedbarbershop.ui.navegacion.NavegacionViewModel
import edu.ucne.appliedbarbershop.utils.Components.ComboFecha

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroClienteScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    id: Int = 0,
    viewModel: ClienteViewModel = hiltViewModel()
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
                        viewModel.save(localContext, navController, navegacionViewModel)
                    }) {
                    Text(text = "Siguiente")
                }
            }
        }
    ) {
        it
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 28.dp, start = 28.dp, bottom = 20.dp, top = 68.dp)
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
                onValueChange = { viewModel.onCelularChange(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            ComboFecha(
                label = "Fecha Nacimiento",
                value = viewModel.fechaNacimiento,
                onValueChange = { viewModel.onFechaNacimientoChange(it) }
            )
        }
    }
}