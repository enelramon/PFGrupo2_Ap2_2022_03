package edu.ucne.appliedbarbershop

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.ui.perfiles.PerfilViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaMisPerfilesScreen(
    navController: NavController,
    viewModel: PerfilViewModel = hiltViewModel()
) {

}