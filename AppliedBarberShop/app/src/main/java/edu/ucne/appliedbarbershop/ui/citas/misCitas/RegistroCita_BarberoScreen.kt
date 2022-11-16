package edu.ucne.appliedbarbershop

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.ui.citas.CitaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroCita_BarberoScreen(
    navController: NavController,
    id: Int = 0,
    viewModel: CitaViewModel // Se debe inicializar el viewModel y pasarslo
) {

}