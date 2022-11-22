
package edu.ucne.appliedbarbershop

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.ui.citas.CitaViewModel
import edu.ucne.appliedbarbershop.ui.navegacion.NavegacionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroCita_ReservacionScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    viewModel: CitaViewModel // Se debe inicializar el viewModel y pasarslo
) {
    val localContext = LocalContext.current
    Scaffold(
        floatingActionButton = {
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
                    Text(text = "Agendar")
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
            OutlinedTextField(
                label = { Text(text = "Fecha") },
                value = viewModel.fch,
                onValueChange = { viewModel.onFchChange(it) })
            OutlinedTextField(
                label = { Text(text = "Hora") },
                value = viewModel.hra,
                onValueChange = { viewModel.onHraChange(it) })
        }
    }
}