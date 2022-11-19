package edu.ucne.appliedbarbershop

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.ui.servicios.ServicioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroServicioScreen(
    navController: NavController,
    id: Int = 0,
    viewModel: ServicioViewModel = hiltViewModel()
) {

}