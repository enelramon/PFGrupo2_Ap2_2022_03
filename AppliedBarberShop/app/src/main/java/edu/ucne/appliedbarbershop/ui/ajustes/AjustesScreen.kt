package edu.ucne.appliedbarbershop

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.ui.intro.AjustesViewModel
import edu.ucne.appliedbarbershop.ui.navegacion.NavegacionViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjustesScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    viewModel: AjustesViewModel = hiltViewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(39.dp))
        Row() {
            OutlinedTextField(
                label = { Text(text = "Codigo de Barbero") },
                value = viewModel.codigoBarbero,
                onValueChange = { viewModel.onCodigoBarberoChange(it) },
                trailingIcon = {
                    Column(modifier = Modifier.padding(end = 10.dp)) {
                        IconButton(onClick = {/*TODO /metodo para validar el codigo de barbero*/}) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                }
            )
        }
    }
}