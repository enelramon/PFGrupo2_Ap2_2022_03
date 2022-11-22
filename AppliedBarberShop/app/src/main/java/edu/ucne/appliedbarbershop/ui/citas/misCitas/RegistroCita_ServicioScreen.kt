
package edu.ucne.appliedbarbershop

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import edu.ucne.appliedbarbershop.utils.Components.CardComponent
import edu.ucne.appliedbarbershop.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroCita_ServicioScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    viewModel: CitaViewModel // Se debe inicializar el viewModel y pasarslo
) {
    val localContext = LocalContext.current
    Scaffold(
        floatingActionButton = {
            Button(onClick = { navController.navigate(Screen.RegistroCita_ReservacionScreen.Route) }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
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
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(navegacionViewModel.servicios) {
                    CardComponent(
                        titulo = it.nombre ?: "",
                        selected = (it == viewModel.servicio),
                        onClick = {
                            viewModel.eligeServicio(it)
                        }
                    )
                }
            }
        }
    }
}