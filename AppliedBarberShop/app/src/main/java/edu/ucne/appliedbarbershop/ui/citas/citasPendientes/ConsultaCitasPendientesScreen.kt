package edu.ucne.appliedbarbershop

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.ui.citas.CitaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaCitasPendientesScreen(
    navController: NavController,
    viewModel: CitaViewModel = hiltViewModel()
) {

}