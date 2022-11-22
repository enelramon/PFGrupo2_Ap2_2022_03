package edu.ucne.appliedbarbershop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.ui.clientees.PerfilViewModel
import edu.ucne.appliedbarbershop.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaMisClientesScreen(
    navController: NavController,
    viewModel: PerfilViewModel = hiltViewModel()
) {
    val localContext = LocalContext.current
    Scaffold(
        floatingActionButton = {
            Button(onClick = { navController.navigate(Screen.RegistroPerfilScreen.Route)}) {
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
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(39.dp))

            Card() {

            }
        }
    }
}